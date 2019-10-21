package si.rso.notifications.sms;


import si.rso.rest.exceptions.ServiceCallException;

public class SmsException extends ServiceCallException {
    
    public SmsException(String message) {
        super(message);
    }
    
    public SmsException(String message, Throwable cause) {
        super(message, cause);
    }
}
