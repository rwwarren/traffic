import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;
import javax.ws.rs.client.Client;

public class TrafficService extends Application<ServiceServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new TrafficService().run(args);
    }

    public void run(ServiceServiceConfiguration serviceServiceConfiguration, Environment environment) throws Exception {
        final Client client = new JerseyClientBuilder(environment).build("client");
        environment.jersey().register(new Why(client));

    }
}