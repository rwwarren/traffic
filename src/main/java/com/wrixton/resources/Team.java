package com.wrixton.resources;

import com.codahale.metrics.annotation.Timed;
import com.wrixton.dao.TeamScheduleDAO;
import com.wrixton.dtos.TeamInfoDTO;
import com.wrixton.model.Model;
import com.wrixton.model.TeamInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/team")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Team {

    private final TeamScheduleDAO teamScheduleDAO;

    public Team(TeamScheduleDAO teamScheduleDAO) {
        this.teamScheduleDAO = teamScheduleDAO;
    }

    @GET
    @Path("{teamName}")
    @Timed
    public TeamInfoDTO getByTeamName(@PathParam("teamName") String teamName) throws Exception {
        TeamInfo teamInfo = teamScheduleDAO.getTeamSchedule(teamName.replace("%20", " "));
        return teamInfo.toDTO();
    }

    @POST
    @Path("/add")
    @Timed
    public TeamInfoDTO addTeamInfo(TeamInfoDTO teamInfo) throws Exception {
        return teamScheduleDAO.addTeamInfo(Model.fromDTO(teamInfo)) == 1 ? teamInfo : null;
    }

}
