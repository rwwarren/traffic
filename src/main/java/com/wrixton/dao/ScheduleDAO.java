package com.wrixton.dao;

import com.wrixton.mapper.TeamScheduleMapper;
import com.wrixton.model.Schedule;
import com.wrixton.model.TeamInfo;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;

@RegisterMapper(TeamScheduleMapper.class)
public interface ScheduleDAO {

    @SqlQuery("SELECT * FROM traffic.schedule where team_name = :teamName")
    Schedule getScheduleByTeam(@Bind("teamName") String teamName);

    @SqlQuery("SELECT * FROM traffic.schedule where market_name = :marketName join traffic.market m on m.id = market_id")
    Schedule getScheduleByMarket(@Bind("marketName") String marketName);

//    @SqlUpdate("INSERT INTO traffic.schedule(team_name, schedule_url) VALUES (:teamName, :scheduleUrl)")
//    int addTeamInfo(@BindBean TeamInfo teamInfo);

}
