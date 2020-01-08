package si.rso.notifications.sms.config;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("notifications.sms")
public class SmsConfig {
    
    @ConfigValue("api-sid")
    private String apiSid;
    
    @ConfigValue("api-key")
    private String apiKey;
    
    @ConfigValue("number")
    private String number;
    
    public String getApiSid() {
        return apiSid;
    }
    
    public void setApiSid(String apiSid) {
        this.apiSid = apiSid;
    }
    
    public String getApiKey() {
        return apiKey;
    }
    
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }
    
    public String getNumber() {
        return number;
    }
    
    public void setNumber(String number) {
        this.number = number;
    }
}
