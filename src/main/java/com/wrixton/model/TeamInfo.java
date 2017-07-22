package com.wrixton.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.net.URL;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamInfo {

    private String teamName;
    private String scheduleUrl;

    public TeamInfo(@JsonProperty("teamName") String teamName,
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
