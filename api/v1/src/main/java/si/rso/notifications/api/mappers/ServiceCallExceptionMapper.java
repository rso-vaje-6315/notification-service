package si.rso.notifications.api.mappers;

import si.rso.rest.exceptions.ServiceCallException;
import si.rso.rest.exceptions.dto.ExceptionResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ServiceCallExceptionMapper implements ExceptionMapper<ServiceCallException> {

    @Override
    public Response toResponse(ServiceCallException exception) {
        ExceptionResponse response = new ExceptionResponse();
        response.setStatusCode(exception.getStatus());
        response.setMessage(String.format("Service %s is not available!", exception.getServiceName()));
        return Response.status(exception.getStatus()).entity(response).build();
    }

}