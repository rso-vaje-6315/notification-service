package si.rso.notifications.sms;


import si.rso.rest.exceptions.RestException;

import javax.ws.rs.core.Response;

public class SmsException extends RestException {
    
    public SmsException(String message) {
        super(message, Response.Status.SERVICE_UNAVAILABLE);
    }
}
