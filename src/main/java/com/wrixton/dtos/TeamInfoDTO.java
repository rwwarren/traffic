package com.wrixton.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamInfoDTO {

    private String teamName;
    private String scheduleUrl;

    public TeamInfoDTO(@JsonProperty("teamName") String teamName,
                       @JsonProperty("scheduleUrl") String scheduleUrl) {
        this.teamName = teamName;
        this.scheduleUrl = scheduleUrl;
    }

    @JsonProperty
    public String getTeamName() {
        return teamName;
    }

    @JsonProperty
    public String getScheduleUrl() {
        return scheduleUrl;
    }
}
