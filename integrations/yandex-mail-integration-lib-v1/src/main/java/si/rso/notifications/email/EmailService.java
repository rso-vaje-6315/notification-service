package si.rso.notifications.email;

public interface EmailService {
    
    void sendEmail(String to, String subject, String htmlContent) throws EmailException;
    
}
