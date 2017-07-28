package com.wrixton.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamScheduleDTO {

    private final String teamName;
    private final URL eventUrl;
    private final LocalDateTime eventDateTime;
    private final EventTypeDTO eventType;
    private final LocationDTO location;

    public TeamScheduleDTO(@JsonProperty("teamName") String teamName,
                           @JsonProperty("eventUrl") URL eventUrl,
                           @JsonProperty("eventDateTime") LocalDateTime eventDateTime,
                           @JsonProperty("eventType") EventTypeDTO eventType,
                           @JsonProperty("location") LocationDTO location) {
        this.teamName = teamName;
        this.eventUrl = eventUrl;
        this.eventDateTime = eventDateTime;
        this.eventType = eventType;
        this.location = location;
    }

    public String getTeamName() {
        return teamName;
    }

    public URL getEventUrl() {
        return eventUrl;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public EventTypeDTO getEventType() {
        return eventType;
    }

    public LocationDTO getLocation() {
        return location;
    }

    @Override
    public String toString() {
        return "TeamScheduleDTO{" +
                "teamName='" + teamName + '\'' +
                ", eventUrl=" + eventUrl +
                ", eventDateTime=" + eventDateTime +
                ", eventType=" + eventType +
                ", location=" + location +
                '}';
    }
}
