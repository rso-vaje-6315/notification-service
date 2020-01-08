package si.rso.notifications.services;

import si.rso.event.streaming.EventStreamMessage;

public interface EventStreamingService {
    
    void handleMessage(EventStreamMessage message);
    
}
