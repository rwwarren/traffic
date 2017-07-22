package com.wrixton.dao;

import com.wrixton.mapper.TeamScheduleMapper;
import com.wrixton.model.TeamInfo;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(TeamScheduleMapper.class)
public interface TeamScheduleDAO {

    @SqlQuery("SELECT * FROM traffic.team_info where team_name = :teamName")
    TeamInfo getTeamSchedule(@Bind("teamName") String teamName);

    @SqlUpdate("INSERT INTO traffic.team_info(team_name, schedule_url) VALUES (:teamName, :scheduleUrl)")
    int addTeamInfo(@BindBean TeamInfo teamInfo);

}
