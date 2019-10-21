package si.rso.notifications.services.impl;

import si.rso.notifications.services.NotificationService;
import si.rso.notifications.sms.SmsService;


import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class NotificationServiceImpl implements NotificationService {
    
    @Inject
    private SmsService smsService;
    
    @Override
    public void notifyAllChannels() {
    
    }
    
    @Override
    public void notifySms() {
        smsService.sendSms("", "Hello world, testing automata", "Jst");
    }
    
    @Override
    public void notifyMail() {
    
    }
}
