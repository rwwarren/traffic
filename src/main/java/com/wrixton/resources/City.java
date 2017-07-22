package com.wrixton.resources;

import com.codahale.metrics.annotation.Timed;
import com.wrixton.dao.CityTeamsDAO;
import com.wrixton.dao.TeamScheduleDAO;
import com.wrixton.dtos.CityTeamDTO;
import com.wrixton.model.CityTeam;
import com.wrixton.model.CityTeams;
import com.wrixton.model.TeamInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/city")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class City {

    private final CityTeamsDAO cityTeamsDAO;
    private final TeamScheduleDAO teamScheduleDAO;

    public City(CityTeamsDAO cityTeamsDAO, TeamScheduleDAO teamScheduleDAO) {
        this.cityTeamsDAO = cityTeamsDAO;
        this.teamScheduleDAO = teamScheduleDAO;
    }

    @GET
    @Path("{cityName}")
    @Timed
    public CityTeams getByTeamName(@PathParam("cityName") String cityName) throws Exception {
        List<String> teams = cityTeamsDAO.getCityTeams(cityName);
        return new CityTeams(cityName, teams);
    }

    @POST
    @Path("/add")
    @Timed
    public CityTeamDTO addTeamInfo(CityTeamDTO cityTeam) throws Exception {
        TeamInfo teamInfo = teamScheduleDAO.getTeamSchedule(cityTeam.getTeamName());
        return cityTeamsDAO.addCityTeam(new CityTeam(cityTeam.getCityName(), teamInfo.getTeamId())) == 1 ? cityTeam : null;
    }

}
