package com.wrixton.resources;

import com.codahale.metrics.annotation.Timed;
import com.wrixton.dao.CityTeamsDAO;
import com.wrixton.dao.TeamScheduleDAO;
import com.wrixton.dtos.CityTeamDTO;
import com.wrixton.dtos.TeamScheduleDTO;
import com.wrixton.model.CityTeam;
import com.wrixton.model.CityTeams;
import com.wrixton.model.TeamInfo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.stream.Collectors;

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
    public CityTeams getByCityName(@PathParam("cityName") String cityName) throws Exception {
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

    @GET
    @Path("{cityName}/schedule")
    @Timed
    public List<TeamScheduleDTO> getCitySchedule(@PathParam("cityName") String cityName) throws Exception {
        CityTeams teams = getByCityName(cityName);
        //get all the teamInfos
//        return teams.getTeams().stream().map(c -> getTeamSchedule(c)).collect(Collectors.toList());
        return null;
    }


    private TeamScheduleDTO getTeamSchedule(TeamInfo team) {

        return null;
    }

}
