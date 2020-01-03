package si.rso.notifications.services.impl;

import si.rso.event.streaming.EventStreamMessage;
import si.rso.event.streaming.JacksonMapper;
import si.rso.notifications.lib.ChannelNotification;
import si.rso.notifications.lib.NotificationsStreamConfig;
import si.rso.notifications.services.EventStreamingService;
import si.rso.notifications.services.NotificationService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class EventStreamingServiceImpl implements EventStreamingService {
    
    @Inject
    private NotificationService notificationService;
    
    @Override
    public void handleMessage(EventStreamMessage message) {
        if (message != null) {
            if (message.getType().equals(NotificationsStreamConfig.SEND_NOTIFICATION_EVENT_ID)) {
                ChannelNotification notification = JacksonMapper.toEntity(message.getData(), ChannelNotification.class);
                notificationService.notifyChannels(notification);
            }
        }
    }
}
