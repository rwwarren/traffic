package com.wrixton.resources;

import com.codahale.metrics.annotation.Timed;
import com.ticketmaster.api.discovery.DiscoveryApi;
import com.ticketmaster.api.discovery.operation.SearchEventsOperation;
import com.ticketmaster.api.discovery.response.PagedResponse;
import com.ticketmaster.discovery.model.Date;
import com.ticketmaster.discovery.model.Event;
import com.ticketmaster.discovery.model.Events;
import com.wrixton.dao.CityTeamsDAO;
import com.wrixton.dao.TeamScheduleDAO;
import com.wrixton.dtos.CityTeamDTO;
import com.wrixton.dtos.LocationDTO;
import com.wrixton.dtos.TeamScheduleDTO;
import com.wrixton.model.CityTeam;
import com.wrixton.model.CityTeams;
import com.wrixton.model.TeamInfo;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/city")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class City {

    private final CityTeamsDAO cityTeamsDAO;
    private final TeamScheduleDAO teamScheduleDAO;
    private final DiscoveryApi ticketmasterApi;

    public City(CityTeamsDAO cityTeamsDAO, TeamScheduleDAO teamScheduleDAO, DiscoveryApi ticketmasterApi) {
        this.cityTeamsDAO = cityTeamsDAO;
        this.teamScheduleDAO = teamScheduleDAO;
        this.ticketmasterApi = ticketmasterApi;
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
//    public List<String> getCitySchedule(@PathParam("cityName") String cityName) throws Exception {
    public List<TeamScheduleDTO> getCitySchedule(@PathParam("cityName") String cityName) throws Exception {
        CityTeams teams = getByCityName(cityName);
        //get all the teamInfos
//        String format = String.format("https://app.ticketmaster.com/discovery/v2/events.json?postalCode=98102&apikey=%s", apiKey);
//        StringBuilder sb = new StringBuilder();
//        sb.append("https://app.ticketmaster.com/discovery/v2/events.json?")
//                .append(String.format("marketId=%s", "42"))
////                .append(String.format("postalCode=%s", "98134"))
//                .append(String.format("&startDateTime=%s", "2017-07-24T06:00:00Z"))
//                .append(String.format("&endDateTime=%s", "2017-07-25T06:00:00Z"))
////                .append(String.format("&endDateTime=%s", "2017-07-25T06:00:00Z"))
//                .append(String.format("&apikey=%s", "QP0EPhFCwq1PG0leHs0wGWCjzGplrC9r"));

//        getTeamSchedule(new TeamInfo(1l, "Sounders", sb.toString()));
        List<TeamScheduleDTO> events = getEvents();
        return events;
//        getTeamSchedule(new TeamInfo(1l, "Sounders", "https://www.mlb.com/mariners/schedule/2017-07/list"));
//        getTeamSchedule(new TeamInfo(1l, "Sounders", "https://www.soundersfc.com/schedule/"));
//        return teams.getTeams().stream().map(c -> getTeamSchedule(c)).collect(Collectors.toList());
//        return new ArrayList<>();
    }


    private TeamScheduleDTO getTeamSchedule(TeamInfo team) {

        try {
            Document response = Jsoup.connect(team.getScheduleUrl())
//            Connection.Response response = Jsoup.connect(team.getScheduleUrl())
//                    .followRedirects(true)
//                    .ignoreContentType(true)
                    .header("content-type", "application/json;charset=utf-8")
//                    .header("content-type", "application/json")
                    .ignoreContentType(true)
                    .get();
//                    .execute();
//            Document document = Jsoup.connect(team.getScheduleUrl()).get();
//            String body = response.body();
            Elements elements = response.select("events");
            System.out.println(response);
            return new TeamScheduleDTO("Sounders", null, null);
        } catch (Exception e) {
            return null;
        }
    }

    private List<TeamScheduleDTO> getEvents(){
        try {
            List<Event> events = ticketmasterApi.searchEvents(new SearchEventsOperation().marketId("42").startDateTime("2017-07-24T06:00:00Z").endDateTime("2017-07-25T06:00:00Z")).getContent().getEvents();
            System.out.println(events);
            return events.stream()
                    .filter(current -> current.getDates().getStart().getLocalTime() != null)
                    .map(current -> {
                        Date.Start start = current.getDates().getStart();
                        LocalDate localDate = LocalDate.parse(start.getLocalDate());
                        LocalTime localTime = LocalTime.parse(start.getLocalTime());
                        LocalDateTime localDateTime = LocalDateTime.of(localDate, localTime);
                        return new TeamScheduleDTO(current.getName(), localDateTime, new LocationDTO(1.1, 1.1));
                    }).collect(Collectors.toList());
        } catch (Exception e) {
            System.out.println(e);
//            throw new e;
            return new ArrayList<>();
        }
    }

}
