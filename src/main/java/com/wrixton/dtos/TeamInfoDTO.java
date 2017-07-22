package com.wrixton.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TeamInfoDTO {

    private long teamId;
    private String teamName;
    private String scheduleUrl;

    public TeamInfoDTO(@JsonProperty("teamId") long teamId,
                       @JsonProperty("teamName") String teamName,
                       @JsonProperty("scheduleUrl") String scheduleUrl) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.scheduleUrl = scheduleUrl;
    }

    @JsonProperty
    public long getTeamId() {
        return teamId;
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
