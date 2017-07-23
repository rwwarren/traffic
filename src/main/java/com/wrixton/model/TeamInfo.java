package com.wrixton.model;

import com.wrixton.dtos.TeamInfoDTO;

public class TeamInfo implements Model<TeamInfoDTO> {

    private Long teamId;
    private String teamName;
    private String scheduleUrl;

    public TeamInfo(Long teamId, String teamName, String scheduleUrl) {
        this.teamId = teamId;
        this.teamName = teamName;
        this.scheduleUrl = scheduleUrl;
    }

    public Long getTeamId() {
        return teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getScheduleUrl() {
        return scheduleUrl;
    }

    @Override
    public TeamInfoDTO toDTO(){
        return new TeamInfoDTO(teamName, scheduleUrl);
    }

    public static TeamInfo fromDTO(TeamInfoDTO dto){
        return new TeamInfo(null, dto.getTeamName(), dto.getScheduleUrl());
    }
}
