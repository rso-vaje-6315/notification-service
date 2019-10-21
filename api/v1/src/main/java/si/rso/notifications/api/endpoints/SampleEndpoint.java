package si.rso.notifications.api.endpoints;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;

import com.kumuluz.ee.rest.beans.QueryParameters;
import si.rso.notifications.services.NotificationService;

@Path("/sample")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SampleEndpoint {
    
    @Context
    protected UriInfo uriInfo;
    
    @Inject
    private NotificationService notificationService;
    
    @GET
    public Response getGreetings() {
        QueryParameters query = QueryParameters.query(uriInfo.getRequestUri().getQuery()).build();
        List<String> samples = null;
        long samplesCount = 0;
        return Response.ok(samples).header("X-Total-Count", samplesCount).build();
    }
    
    @GET
    @Path("/test")
    public Response test() {
        notificationService.notifySms();
        return Response.ok().build();
    }

}
