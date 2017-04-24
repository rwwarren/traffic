package resources;

import com.codahale.metrics.annotation.Timed;
import javax.validation.Valid;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import request.FutureLocationRequest;
import request.LocationRequest;

@Path("/why")
@Produces(MediaType.APPLICATION_JSON)
public class Why {

    private final Client client;

    public Why(Client client) {
        this.client = client;
    }

    @GET
    @Path("{city}")
    @Timed
    public Response getEventsInCity(@PathParam("city") String city) {
        final WebTarget target = client.target(String.format("http://gametonight.in/%s/json", city));
        final Invocation.Builder request = target.request();
        final Response response = request.get();
        if (response.getStatus() == 404) {
            return Response.status(404).build();
        }
        return response;
    }


    @POST
    @Path("/between")
    @Timed
    public Response getEventsBetweenLocations(@Valid LocationRequest locationRequest) {
        return Response.ok("works").build();
    }

    @POST
    @Path("/between/future")
    @Timed
    public Response getEventsBetweenLocationsFuture(@Valid FutureLocationRequest futureLocationRequest) {
        return Response.ok("works").build();
    }


}