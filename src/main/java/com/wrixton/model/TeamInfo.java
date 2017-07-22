package com.wrixton.model;

public class TeamInfo {

    private long teamId;
    private String teamName;
    private String scheduleUrl;

    public TeamInfo(long teamId, String teamName, String scheduleUrl) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.scheduleUrl = scheduleUrl;
    }

    public long getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getScheduleUrl() {
        return scheduleUrl;
    }
}
