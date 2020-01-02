package si.rso.notifications.api.endpoints;

import si.rso.notifications.lib.ChannelNotification;
import si.rso.notifications.services.NotificationService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/test")
@RequestScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SampleEndpoint {
    
    @Inject
    private NotificationService notificationService;
    
    @GET
    public Response test2() {
        return Response.ok().build();
    }
    
    @POST
    public Response test(ChannelNotification notification) {
        notificationService.notifyChannels(notification);
        return Response.ok().build();
    }

}
