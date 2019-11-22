package si.rso.notifications.services;

public interface EventStreamingService {
    
    void handleMessage(String rawMessage);
    
}
