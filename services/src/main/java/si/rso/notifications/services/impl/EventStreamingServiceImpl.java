package si.rso.notifications.services.impl;

import org.eclipse.microprofile.faulttolerance.CircuitBreaker;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Timeout;
import si.rso.event.streaming.EventStreamMessage;
import si.rso.event.streaming.JacksonMapper;
import si.rso.notifications.config.ServiceConfig;
import si.rso.notifications.lib.ChannelNotification;
import si.rso.notifications.lib.NotificationsStreamConfig;
import si.rso.notifications.services.EventStreamingService;
import si.rso.notifications.services.NotificationService;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.time.temporal.ChronoUnit;

@ApplicationScoped
public class EventStreamingServiceImpl implements EventStreamingService {
    
    @Inject
    private NotificationService notificationService;
    
    @Inject
    private ServiceConfig serviceConfig;
    
    @Override
    @CircuitBreaker
    @Timeout(value = 5, unit = ChronoUnit.SECONDS)
    @Fallback(fallbackMethod = "messageHandlerFallback")
    public void handleMessage(EventStreamMessage message) {
        if (serviceConfig.isNotificationsEnabled()) {
            if (message != null) {
                if (message.getType().equals(NotificationsStreamConfig.SEND_NOTIFICATION_EVENT_ID)) {
                    ChannelNotification notification = JacksonMapper.toEntity(message.getData(), ChannelNotification.class);
                    notificationService.notifyChannels(notification);
                }
            }
        }
    }
    
    public void messageHandlerFallback(EventStreamMessage message) {
    
    }
}
