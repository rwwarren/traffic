package com.wrixton.dao;

import com.wrixton.mapper.CityTeamsMapper;
import com.wrixton.model.CityTeams;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(CityTeamsMapper.class)
public interface CityTeamsDAO {

    @SqlQuery("SELECT * FROM traffic.team_info where team_name = :teamName")
    CityTeams getTeamSchedule(@Bind("teamName") String teamName);
}
