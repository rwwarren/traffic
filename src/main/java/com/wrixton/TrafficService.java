package com.wrixton;

import com.ticketmaster.api.discovery.DiscoveryApi;
import com.wrixton.collectors.*;
import bundles.FlywayBundle;
import bundles.configuration.FlywayBundleConfiguration;
import com.wrixton.dao.CityTeamsDAO;
import com.wrixton.dao.MarketDAO;
import com.wrixton.dao.TeamScheduleDAO;
import com.wrixton.resources.*;
import io.dropwizard.Application;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import java.util.Collections;
import javax.ws.rs.client.Client;

import com.wrixton.manager.EventManager;
import org.skife.jdbi.v2.DBI;

public class TrafficService extends Application<TrafficServiceConfiguration> {

 private FlywayBundle<TrafficServiceConfiguration> flywayBundle = new FlywayBundle<TrafficServiceConfiguration>("traffic-flyway") {
     @Override
     protected FlywayBundleConfiguration extractBundleConfiguration(TrafficServiceConfiguration config) throws Exception {
         FlywayBundleConfiguration flywayBundleConfiguration = new FlywayBundleConfiguration();
         flywayBundleConfiguration.setDataSourceFactory(config.getDatabaseFlyway());
         flywayBundleConfiguration.setSchemas(Collections.singletonList("traffic"));
         return flywayBundleConfiguration;
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

        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, config.getDatabase(), "postgresql");

        final Client client = new JerseyClientBuilder(environment).build("client");
        final GameTonightCollector gameTonightCollector = new GameTonightCollector(client);
        final ShowboxCollector showboxCollector = new ShowboxCollector();
        final WsdotCollector wsdotCollector = new WsdotCollector();
        final WsccCollector wsccCollector = new WsccCollector();
        final StrangerCollector strangerCollector = new StrangerCollector();
        final EventManager eventManager = new EventManager(gameTonightCollector, showboxCollector, wsdotCollector, wsccCollector);
        final TeamScheduleDAO teamScheduleDAO = jdbi.onDemand(TeamScheduleDAO.class);
        final CityTeamsDAO cityTeamsDAO = jdbi.onDemand(CityTeamsDAO.class);
        final MarketDAO marketDAO = jdbi.onDemand(MarketDAO.class);
        final DiscoveryApi ticketmasterApi = new DiscoveryApi(config.getTicketmasterApiKey());

        environment.jersey().register(new Main());
        environment.jersey().register(new Why(eventManager));
        environment.jersey().register(new What(showboxCollector, wsdotCollector, wsccCollector, strangerCollector, config.getGoogleApiKey()));
        environment.jersey().register(new Team(teamScheduleDAO));
        environment.jersey().register(new City(cityTeamsDAO, teamScheduleDAO, ticketmasterApi));
        environment.jersey().register(new Market(marketDAO));

        final ConnectionHealthCheck healthCheck = new ConnectionHealthCheck(config.getGoogleApiKey());
        environment.healthChecks().register("connections", healthCheck);

    }
}