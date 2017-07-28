CREATE TABLE traffic.market(
  id BIGSERIAL,
  market_name VARCHAR(255) NOT NULL,
  ticketmaster_market_id BIGINT NOT NULL
);

GRANT SELECT, INSERT, UPDATE on traffic.market to traffic_rw;