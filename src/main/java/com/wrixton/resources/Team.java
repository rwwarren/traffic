package com.wrixton.resources;

import com.codahale.metrics.annotation.Timed;
import com.wrixton.dao.TeamScheduleDAO;
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
    public TeamInfo getByTeamName(@PathParam("teamName") String teamName) throws Exception {
        TeamInfo teamInfo = teamScheduleDAO.getTeamSchedule(teamName.replace("%20", " "));
        return teamInfo;
    }

    @POST
    @Path("/add")
    @Timed
    public TeamInfo addTeamInfo(TeamInfo teamInfo) throws Exception {
        return teamScheduleDAO.addTeamInfo(teamInfo) == 1 ? teamInfo : null;
    }

}
