package com.wrixton.dtos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CityTeamDTO {

    private String cityName;
    private String teamName;

    public CityTeamDTO(@JsonProperty("cityName") String cityName,
                       @JsonProperty("teamName") String teamName) {
        this.cityName = cityName;
        this.teamName = teamName;
    }

    @JsonProperty
    public String getCityName() {
        return cityName;
    }

    @JsonProperty
    public String getTeamName() {
        return teamName;
    }

}
