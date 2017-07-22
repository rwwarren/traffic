package com.wrixton.dao;

import com.wrixton.mapper.CityTeamsMapper;
import com.wrixton.model.CityTeams;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

import java.util.List;

@RegisterMapper(CityTeamsMapper.class)
public interface CityTeamsDAO {

    @SqlQuery("SELECT * FROM traffic.teams_per_city tpc join traffic.team_info ti on ti.id = tpc.team_info_id where city_name = :cityName")
    List<String> getCityTeams(@Bind("cityName") String cityName);
}
