package si.rso.notifications.sms.impl;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import si.rso.notifications.sms.SmsException;
import si.rso.notifications.sms.SmsService;
import si.rso.notifications.sms.api.TwilioApi;
import si.rso.notifications.sms.config.SmsConfig;
import si.rso.rest.services.Validator;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.Response;
import java.util.Base64;

@ApplicationScoped
public class TwilioSmsService implements SmsService {
    
    private static final String PHONE_REGEX = "^\\+?[1-9]\\d{1,14}$";
    
    @Inject
    private SmsConfig smsConfig;
    
    @Inject
    private Validator validator;
    
    @Inject
    @RestClient
    private TwilioApi twilioApi;
    
    @Override
    public void sendSms(String phoneNumber, String content) throws SmsException {
        
        validator.assertRegex(phoneNumber, PHONE_REGEX);
        validator.assertNotBlank(content);
        
        try {
            
            Form form = new Form();
            form.param("From", smsConfig.getNumber());
            form.param("To", phoneNumber);
            form.param("Body", content);
            
            Response response = twilioApi.sendSms(smsConfig.getApiSid(), createAuthHeader(), form);
            
            if (response.getStatus() != Response.Status.CREATED.getStatusCode()) {
                System.err.println("Twilio response status: " + response.getStatus());
                throw new SmsException("Return status is not 201!");
            }
        } catch (WebApplicationException e) {
            e.printStackTrace();
            throw new SmsException("Twilio REST call failed: " + e.getMessage());
        }
    }
    
    private String createAuthHeader() {
        String credentials = smsConfig.getApiSid() + ":" + smsConfig.getApiKey();
        return "Basic " + new String(Base64.getEncoder().encode(credentials.getBytes()));
    }
}
