package si.rso.notifications.services.impl;

import com.kumuluz.ee.configuration.utils.ConfigurationUtil;
import si.rso.notifications.email.EmailAttachmentWrapper;
import si.rso.notifications.email.EmailService;
import si.rso.notifications.lib.ChannelNotification;
import si.rso.notifications.lib.EmailNotification;
import si.rso.notifications.lib.SmsNotification;
import si.rso.notifications.services.NotificationService;
import si.rso.notifications.sms.SmsService;
import si.rso.rest.exceptions.ServiceCallException;
import si.rso.rest.exceptions.ValidationException;
import si.rso.rest.services.Validator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@ApplicationScoped
public class NotificationServiceImpl implements NotificationService {
    
    @Inject
    private SmsService smsService;
    
    @Inject
    private EmailService emailService;
    
    @Inject
    private Validator validator;
    
    private static final String SUPPORTED_MEDIA_TYPES = "application/pdf,application/json/image/jpeg,image/png,image/gif,text/plain,text/html,text/csv,application/xml,application/vnd.ms-excel";
    
    @Override
    public void notifyChannels(ChannelNotification notification) {
        
        if (notification.getSms() == null && notification.getEmail() == null) {
            throw new ValidationException("empty.payload")
                .isValidationError()
                .withDescription("Payload is empty! EMAIL or SMS data must be provided!");
        }
        
        try {
            if (notification.getEmail() != null) {
                notifyMail(notification.getEmail());
            }
            if (notification.getSms() != null) {
                notifySms(notification.getSms());
            }
        } catch (ServiceCallException e) {
            e.printStackTrace();
            throw e;
        }
    }
    
    private void notifySms(SmsNotification notification) {
        smsService.sendSms(notification.getPhoneNumber(), notification.getContent());
    }
    
    
    private void notifyMail(EmailNotification notification) {
        try {
            
            EmailAttachmentWrapper attachment = null;
            
            if (notification.getAttachment() != null) {
                validator.assertNotBlank(notification.getAttachment().getUrl());
                validator.assertNotBlank(notification.getAttachment().getName());
    
                attachment = retrieveAttachment(
                    notification.getAttachment().getUrl(),
                    notification.getAttachment().getName()
                );
            }
    
            emailService.sendEmail(
                notification.getEmail(),
                notification.getSubject(),
                notification.getHtmlContent(),
                attachment
            );
            
            if (attachment != null) {
                cleanupFile(attachment.getFile());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private EmailAttachmentWrapper retrieveAttachment(String attachmentUrl, String attachmentName) {
        try {
            Response response = ClientBuilder.newClient()
                .target(attachmentUrl)
                .request(SUPPORTED_MEDIA_TYPES)
                .get();
            
            if (response.getStatus() < 400) {
                String extension = resolveExtension(attachmentName);
                InputStream inputStream = response.readEntity(InputStream.class);
                File file = storeFile(inputStream, attachmentName);
                
                if (file == null) {
                    return null;
                }
                
                EmailAttachmentWrapper wrapper = new EmailAttachmentWrapper();
                wrapper.setMediaType(response.getMediaType().toString());
                wrapper.setFilename(attachmentName);
                wrapper.setExt(extension);
                wrapper.setFile(file);
                return wrapper;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
    @SuppressWarnings("ResultOfMethodCallIgnored")
    private File storeFile(InputStream is, String filename) {
        String tempFilename = new Date().getTime() + "-" + filename;
        String tmpDir = ConfigurationUtil.getInstance().get("notifications.mail.tmp-dir").orElse("temp");
        File tempFile = new File(Paths.get(tmpDir, tempFilename).toString());
        
        try (OutputStream outputStream = new FileOutputStream(tempFile)) {
            byte[] byteArray = new byte[is.available()];
            is.read(byteArray);
            outputStream.write(byteArray);
            return tempFile;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private void cleanupFile(File file) {
        try {
            Files.delete(file.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private String resolveExtension(String filename) {
        String[] fileParts = filename.split("\\.");
        return fileParts[fileParts.length - 1];
    }
}
