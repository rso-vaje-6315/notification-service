package si.rso.notifications.email;

import si.rso.rest.exceptions.RestException;

import javax.ws.rs.core.Response;

public class EmailException extends RestException {
    
    public EmailException(String message) {
        super(message, Response.Status.SERVICE_UNAVAILABLE);
    }
}
