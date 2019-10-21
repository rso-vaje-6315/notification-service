package si.rso.notifications.email;

import si.rso.rest.exceptions.ServiceCallException;

public class EmailException extends ServiceCallException {
    
    public EmailException(String message) {
        super(message);
    }
    
    public EmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
