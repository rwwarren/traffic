package com.wrixton;

import com.wrixton.collectors.*;
import bundles.FlywayBundle;
import bundles.configuration.FlywayBundleConfiguration;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import javax.ws.rs.client.Client;

import com.wrixton.manager.EventManager;
import com.wrixton.resources.ConnectionHealthCheck;
import com.wrixton.resources.What;
import com.wrixton.resources.Why;

public class TrafficService extends Application<TrafficServiceConfiguration> {

//    public FlywayBundle flywayBundle = new FlywayBundle("flyway-bundle")
    public FlywayBundle<TrafficServiceConfiguration> flywayBundle = new FlywayBundle<TrafficServiceConfiguration>() {
        @Override
        public DataSourceFactory getDataSourceFactory(TrafficServiceConfiguration configuration) {
            return configuration.getDataSourceFactory();
        }

        @Override
        public FlywayFactory getFlywayFactory(TrafficServiceConfiguration configuration) {
            return configuration.getFlywayFactory();
        }
    };

    @Override
    public void initialize(Bootstrap<TrafficServiceConfiguration> bootstrap) {
//        super.initialize(bootstrap);
        bootstrap.addBundle(flywayBundle);
    }

    public static void main(String[] args) throws Exception {
        new TrafficService().run(args);
    }

    public void run(TrafficServiceConfiguration config, Environment environment) throws Exception {
//        Fly


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