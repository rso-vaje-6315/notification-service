package si.rso.notifications.services;

import si.rso.notifications.lib.ChannelNotification;

public interface NotificationService {
    
    void notifyChannels(ChannelNotification notification);
    
}
