package com.wrixton.mapper;

import com.wrixton.model.Market;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MarketMapper implements ResultSetMapper<Market> {
    @Override
    public Market map(int i, ResultSet rs, StatementContext statementContext) throws SQLException {
        return new Market(rs.getLong("id"), rs.getLong("ticketmaster_market_id"), rs.getString("market_name"));
    }
}
