package si.rso.notifications.services;

public interface NotificationService {
    
    void notifyAllChannels();
    
    void notifySms();
    
    void notifyMail();
}
