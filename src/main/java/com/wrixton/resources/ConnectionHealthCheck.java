package com.wrixton.resources;

import com.codahale.metrics.health.HealthCheck;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;

public class ConnectionHealthCheck extends HealthCheck {

    private final String googleApiKey;

    public ConnectionHealthCheck(String googleApiKey) {
        this.googleApiKey = googleApiKey;
    }

    @Override
    protected Result check() throws Exception {
        try {
            final GeoApiContext context = new GeoApiContext().setApiKey(googleApiKey);
            final GeocodingResult[] results = GeocodingApi.geocode(context, "1 main st, edmonds wa 98026").await();
            if (results == null) {
                return Result.unhealthy("Google api not working");
            }
        } catch (Exception e) {
            return Result.unhealthy("Google api not working");
        }
        return Result.healthy();
    }

}

//public class TemplateHealthCheck extends HealthCheck {
//    private final String template;
//
//    public TemplateHealthCheck(String template) {
//        this.template = template;
//    }
//
//    @Override
//    protected Result check() throws Exception {
//        final String saying = String.format(template, "TEST");
//        if (!saying.contains("TEST")) {
//            return Result.unhealthy("template doesn't include a name");
//        }
//        return Result.healthy();
//    }
//}
