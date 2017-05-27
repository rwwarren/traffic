package com.wrixton;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayFactory;
import org.hibernate.validator.constraints.NotEmpty;

public class TrafficServiceConfiguration extends Configuration {

    @NotEmpty
    private String googleApiKey;

    @JsonProperty
    public String getGoogleApiKey() {
        return googleApiKey;
    }

    public DataSourceFactory getDataSourceFactory() {
        return null;
    }

    public FlywayFactory getFlywayFactory() {
        return null;
    }

}
