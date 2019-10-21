package si.rso.notifications.email.impl;

import si.rso.notifications.email.EmailException;
import si.rso.notifications.email.EmailService;
import si.rso.notifications.email.config.EmailConfig;
import si.rso.rest.services.Validator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

@ApplicationScoped
public class YandexEmailService implements EmailService {
    
    @Inject
    private EmailConfig emailConfig;
    
    @Inject
    private Validator validator;
    
    @Override
    public void sendEmail(String to, String subject, String htmlContent, String textContent) throws EmailException {
        
        validator.assertNotBlank(to);
        validator.assertNotBlank(subject);
        validator.assertNotBlank(htmlContent);
        
        try {
            Properties properties = createProperties();
            
            Session session = Session.getInstance(properties);
            Message message = createMessage(session, to, subject, htmlContent, textContent);
    
            Transport transport = session.getTransport("smtp");
            transport.connect(emailConfig.getHost(), emailConfig.getUsername(), emailConfig.getPassword());
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
            throw new EmailException("Yandex SMTP call failed: " + e.getMessage());
        }
        
    }
    
    private MimeMessage createMessage(Session session, String to, String subject, String htmlContent, String textContent) throws Exception {
        MimeMessage email = new MimeMessage(session);
        InternetAddress recipient = new InternetAddress(to);
        InternetAddress sender = new InternetAddress(emailConfig.getUsername(), emailConfig.getDisplayName());
        
        email.setFrom(sender);
        email.addRecipient(Message.RecipientType.TO, recipient);
        
        Multipart content = new MimeMultipart();
        
        if (textContent != null && !textContent.isEmpty()) {
            BodyPart plainTextPart = new MimeBodyPart();
            plainTextPart.setText(textContent);
            content.addBodyPart(plainTextPart);
        }
        
        BodyPart htmlTextPart = new MimeBodyPart();
        htmlTextPart.setContent(htmlContent, "text/html; charset=utf-8");
        content.addBodyPart(htmlTextPart);
        
        email.setSubject(subject);
        email.setContent(content);
        
        return email;
    }
    
    private Properties createProperties() {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", emailConfig.getHost());
        properties.put("mail.smtp.user", emailConfig.getUsername());
        properties.put("mail.smtp.password", emailConfig.getPassword());
        properties.put("mail.smtp.port", emailConfig.getPort());
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");
        return properties;
    }
}
