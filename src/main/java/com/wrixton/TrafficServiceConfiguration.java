package com.wrixton;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayFactory;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotEmpty;

public class TrafficServiceConfiguration extends Configuration {

    @NotEmpty
    private String googleApiKey;

    @Valid
    @NotNull
    @JsonProperty
    private DataSourceFactory databaseFlyway;

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

    public DataSourceFactory getDatabaseFlyway() {
        return databaseFlyway;
    }

}
