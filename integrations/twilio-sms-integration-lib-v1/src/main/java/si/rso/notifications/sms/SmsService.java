package si.rso.notifications.sms;

public interface SmsService {
    
    void sendSms(String phoneNumber, String content, String sender);
    
}
