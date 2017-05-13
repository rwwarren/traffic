import collectors.*;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.setup.Environment;
import javax.ws.rs.client.Client;

import manager.EventManager;
import resources.ConnectionHealthCheck;
import resources.What;
import resources.Why;

public class TrafficService extends Application<TrafficServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new TrafficService().run(args);
    }

    public void run(TrafficServiceConfiguration config, Environment environment) throws Exception {
        final Client client = new JerseyClientBuilder(environment).build("client");
        final GameTonightCollector gameTonightCollector = new GameTonightCollector(client);
        final ShowboxCollector showboxCollector = new ShowboxCollector();
        final WsdotCollector wsdotCollector = new WsdotCollector();
        final WsccCollector wsccCollector = new WsccCollector();
        final StrangerCollector strangerCollector = new StrangerCollector();
        final EventManager eventManager = new EventManager(gameTonightCollector, showboxCollector, wsdotCollector, wsccCollector);
        environment.jersey().register(new Why(eventManager));
        environment.jersey().register(new What(showboxCollector, wsdotCollector, wsccCollector, strangerCollector, config.getGoogleApiKey()));

        final ConnectionHealthCheck healthCheck = new ConnectionHealthCheck(config.getGoogleApiKey());
        environment.healthChecks().register("connections", healthCheck);

    }
}