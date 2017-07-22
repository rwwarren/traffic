package com.wrixton.mapper;

import com.wrixton.model.CityTeams;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CityTeamsMapper implements ResultSetMapper<String> {
    @Override
    public String map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
        return rs.getString("team_name");
    }
}
