package si.rso.notifications.sms.impl;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import si.rso.notifications.sms.SmsService;
import si.rso.notifications.sms.api.TwilioApi;
import si.rso.notifications.sms.config.SmsConfig;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Form;
import java.util.Base64;

@ApplicationScoped
public class TwilioSmsService implements SmsService {
    
    @Inject
    private SmsConfig smsConfig;
    
    @Inject
    @RestClient
    private TwilioApi twilioApi;
    
    @Override
    public void sendSms(String phoneNumber, String content, String sender) {
        
        try {
            
            Form form = new Form();
            form.param("From", smsConfig.getNumber());
            form.param("To", phoneNumber);
            form.param("Body", content);
            
            twilioApi.sendSms(smsConfig.getApiSid(), createAuthHeader(), form);
        } catch (WebApplicationException e) {
            e.printStackTrace();
        }
        
        
        /*Twilio.init(smsConfig.getApiSid(), smsConfig.getApiKey());
    
        Message message = Message.creator(
            new PhoneNumber(phoneNumber),
            new PhoneNumber(smsConfig.getNumber()),
            content
        ).create();
    
        System.err.println(message.getStatus().toString());*/
    }
    
    private String createAuthHeader() {
        String credentials = smsConfig.getApiSid() + ":" + smsConfig.getApiKey();
        return "Basic " + new String(Base64.getEncoder().encode(credentials.getBytes()));
    }
}
