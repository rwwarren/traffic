package com.wrixton.resources;

import com.codahale.metrics.annotation.Timed;
import com.wrixton.dao.ScheduleDAO;
import com.wrixton.dao.TeamScheduleDAO;
import com.wrixton.dtos.ScheduleDTO;
import com.wrixton.dtos.TeamInfoDTO;
import com.wrixton.model.TeamInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/schedule")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class Schedule {

    private final ScheduleDAO scheduleDAO;

    public Schedule(ScheduleDAO scheduleDAO) {
        this.scheduleDAO = scheduleDAO;
    }

    @GET
    @Path("/by-team-name/{teamName}")
    @Timed
    public ScheduleDTO getScheduleByTeamName(@PathParam("teamName") String teamName) throws Exception {
        return scheduleDAO.getScheduleByTeam(teamName).toDTO();
    }

    @GET
    @Path("/by-market-name/{marketName}")
    @Timed
    public ScheduleDTO getScheduleByMarketName(@PathParam("marketName") String marketName) throws Exception {
        return scheduleDAO.getScheduleByMarket(marketName).toDTO();
    }

}
