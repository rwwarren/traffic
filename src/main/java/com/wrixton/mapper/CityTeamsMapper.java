package com.wrixton.mapper;

import com.wrixton.model.CityTeams;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CityTeamsMapper implements ResultSetMapper<CityTeams> {
    @Override
    public CityTeams map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return null;
    }
}
