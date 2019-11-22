package si.rso.notifications.services.impl;

import si.rso.event.streaming.EventStreamMessage;
import si.rso.event.streaming.EventStreamMessageParser;
import si.rso.event.streaming.JacksonMapper;
import si.rso.notifications.lib.ChannelNotification;
import si.rso.notifications.lib.NotificationsStreamConfig;
import si.rso.notifications.services.EventStreamingService;
import si.rso.notifications.services.NotificationService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Optional;

@ApplicationScoped
public class EventStreamingServiceImpl implements EventStreamingService {
    
    @Inject
    private NotificationService notificationService;
    
    @Override
    public void handleMessage(String rawMessage) {
        Optional<EventStreamMessage> eventStreamMessage = EventStreamMessageParser.decodeMessage(rawMessage);
        
        if (eventStreamMessage.isPresent()) {
            if (eventStreamMessage.get().getType().equals(NotificationsStreamConfig.KAFKA_SEND_NOTIFICATION_EVENT_ID)) {
                ChannelNotification notification = JacksonMapper.toEntity(eventStreamMessage.get().getData(), ChannelNotification.class);
                notificationService.notifyChannels(notification);
            }
        }
    }
}
