package com.wrixton.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamScheduleDTO {

    private final String teamName;
    private final LocalDateTime eventDateTime;
    private final LocationDTO location;

    public TeamScheduleDTO(@JsonProperty("teamName") String teamName,
                           @JsonProperty("eventDateTime") LocalDateTime eventDateTime,
                           @JsonProperty("location") LocationDTO location) {
        this.teamName = teamName;
        this.eventDateTime = eventDateTime;
        this.location = location;
    }
}
