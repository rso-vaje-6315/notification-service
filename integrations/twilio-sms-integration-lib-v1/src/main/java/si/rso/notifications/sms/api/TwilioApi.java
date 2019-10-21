package si.rso.notifications.sms.api;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.Dependent;
import javax.ws.rs.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RegisterRestClient(configKey = "twilio-api")
@Dependent
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface TwilioApi {
    
    @POST
    @Path("/Accounts/{accountSid}/Messages.json")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    Response sendSms(@PathParam("accountSid") String accountSid, @HeaderParam("Authorization") String authorizationHeader, Form form);
    
}
