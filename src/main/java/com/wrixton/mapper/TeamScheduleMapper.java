package com.wrixton.mapper;

import com.wrixton.model.TeamInfo;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamScheduleMapper implements ResultSetMapper<TeamInfo> {
    @Override
    public TeamInfo map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
        return new TeamInfo(rs.getLong("id"), rs.getString("team_name"), rs.getString("schedule_url"));
    }
}
