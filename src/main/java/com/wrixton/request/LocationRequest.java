package com.wrixton.request;

import com.wrixton.dtos.LocationDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LocationRequest {

    private final LocationDTO start;
    private final LocationDTO end;

    public LocationRequest(@JsonProperty("start") LocationDTO start,
                           @JsonProperty("end") LocationDTO end) {
        this.start = start;
        this.end = end;
    }
}
