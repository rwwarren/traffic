import com.codahale.metrics.annotation.Timed;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/why")
@Produces(MediaType.APPLICATION_JSON)
public class Why {

    private final Client client;

    public Why(Client client){
        this.client = client;
    }

    @GET
    @Path("{city}")
    @Timed
    public Response sayHello(@PathParam("city") String city) {
//    public String sayHello(@PathParam("city") String city) {
        //http://gametonight.in/{city}/json
        final WebTarget target = client.target(String.format("http://gametonight.in/%s/json", city));
        final Response response = target.request().get();
        return response;
//        return "Because " + city;
    }


}