import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;
import javax.ws.rs.client.Client;

import manager.EventManager;
import resources.What;
import resources.Why;

public class TrafficService extends Application<TrafficServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new TrafficService().run(args);
    }

    public void run(TrafficServiceConfiguration trafficServiceConfiguration, Environment environment) throws Exception {
        final Client client = new JerseyClientBuilder(environment).build("client");
        final EventManager eventManager = new EventManager(client);
        environment.jersey().register(new Why(eventManager));
        environment.jersey().register(new What());

    }
}