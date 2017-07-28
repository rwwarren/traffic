package com.wrixton.dao;

import com.wrixton.model.Market;
import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;

public interface MarketDAO {

    @SqlQuery("SELECT * FROM traffic.market where market_name = :marketName")
    Market getTeamSchedule(@Bind("marketName") String marketName);

    @SqlUpdate("INSERT INTO traffic.market(ticketmaster_market_id, market_name) VALUES (:ticketMasterMarketId, :marketName)")
    int addTeamInfo(@BindBean Market market);
}
