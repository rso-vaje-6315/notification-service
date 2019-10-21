package si.rso.notifications.services.impl;

import si.rso.notifications.email.EmailService;
import si.rso.notifications.lib.AllNotification;
import si.rso.notifications.lib.EmailNotification;
import si.rso.notifications.lib.SmsNotification;
import si.rso.notifications.services.NotificationService;
import si.rso.notifications.sms.SmsService;
import si.rso.rest.services.Validator;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class NotificationServiceImpl implements NotificationService {
    
    @Inject
    private SmsService smsService;
    
    @Inject
    private EmailService emailService;
    
    @Inject
    private Validator validator;
    
    @Override
    public void notifyAllChannels(AllNotification notification) {
        
        validator.assertNotNull(notification.getSms());
        validator.assertNotNull(notification.getEmail());
        
        notifySms(notification.getSms());
        notifyMail(notification.getEmail());
    }
    
    @Override
    public void notifySms(SmsNotification notification) {
        smsService.sendSms(notification.getPhoneNumber(), notification.getContent());
    }
    
    @Override
    public void notifyMail(EmailNotification notification) {
        emailService.sendEmail(
            notification.getEmail(),
            notification.getSubject(),
            notification.getHtmlContent()
        );
    }
}
