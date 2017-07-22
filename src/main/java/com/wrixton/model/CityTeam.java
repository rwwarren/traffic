package com.wrixton.model;

public class CityTeam {

    private String cityName;
    private long teamInfoId;

    public CityTeam(String cityName, long teamInfoId) {
        this.cityName = cityName;
        this.teamInfoId = teamInfoId;
    }

    public String getCityName() {
        return cityName;
    }

    public long getTeamInfoId() {
        return teamInfoId;
    }
}
