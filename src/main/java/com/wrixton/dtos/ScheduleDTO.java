package com.wrixton.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ScheduleDTO {

    private final String teamName;
    private final String eventUrl;
    private final String eventDateTime;
    private final String eventType;
    private final String locationName;
//    private final Long eventMarket;
    private final double latitude;
    private final double longitude;

    public ScheduleDTO(@JsonProperty("teamName") String teamName,
                       @JsonProperty("eventUrl") String eventUrl,
                       @JsonProperty("eventDateTime") String eventDateTime,
                       @JsonProperty("eventType") String eventType,
                       @JsonProperty("locationName") String locationName,
                       @JsonProperty("latitude") double latitude,
                       @JsonProperty("longitude") double longitude) {
        this.teamName = teamName;
        this.eventUrl = eventUrl;
        this.eventDateTime = eventDateTime;
        this.eventType = eventType;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
