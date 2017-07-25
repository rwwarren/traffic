package com.wrixton.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CityTeams {

    private String cityName;
    private List<String> teams;

    public CityTeams(@JsonProperty("cityName") String cityName,
                     @JsonProperty("teams") List<String> teams) {
        this.cityName = cityName;
        this.teams = teams;
    }

    @JsonProperty
    public String getCityName() {
        return cityName;
    }

    @JsonProperty
    public List<String> getTeams() {
        return teams;
    }
}
