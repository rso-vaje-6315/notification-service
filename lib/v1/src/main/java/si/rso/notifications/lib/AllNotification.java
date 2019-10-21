package si.rso.notifications.lib;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AllNotification {
    
    private SmsNotification sms;
    
    private EmailNotification email;
    
    public SmsNotification getSms() {
        return sms;
    }
    
    public void setSms(SmsNotification sms) {
        this.sms = sms;
    }
    
    public EmailNotification getEmail() {
        return email;
    }
    
    public void setEmail(EmailNotification email) {
        this.email = email;
    }
}
