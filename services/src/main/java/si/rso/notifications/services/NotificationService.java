package si.rso.notifications.services;

import si.rso.notifications.lib.AllNotification;
import si.rso.notifications.lib.EmailNotification;
import si.rso.notifications.lib.SmsNotification;

public interface NotificationService {
    
    void notifyAllChannels(AllNotification notification);
    
    void notifySms(SmsNotification notification);
    
    void notifyMail(EmailNotification notification);
}
