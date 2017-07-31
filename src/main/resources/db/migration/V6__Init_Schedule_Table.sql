CREATE TABLE traffic.event_type (
  event_type VARCHAR(255) NOT NULL PRIMARY KEY
);

ALTER TABLE traffic.market ADD UNIQUE (id);

CREATE TABLE traffic.schedule(
  id BIGSERIAL,
  team_name VARCHAR(255) NOT NULL,
  event_url VARCHAR(255) NOT NULL,
  event_date_time TIMESTAMP NOT NULL,
  event_type VARCHAR(255) NOT NULL REFERENCES traffic.event_type(event_type),
  location_name VARCHAR(255) NOT NULL,
  event_market BIGINT REFERENCES traffic.market(id),
  latitude DOUBLE PRECISION NOT NULL,
  longitude DOUBLE PRECISION NOT NULL
);

GRANT SELECT, INSERT, UPDATE on traffic.event_type to traffic_rw;
GRANT SELECT, INSERT, UPDATE on traffic.schedule to traffic_rw;

CREATE INDEX ON traffic.team_info (team_name);
CREATE INDEX ON traffic.teams_per_city (city_name);
CREATE INDEX ON traffic.market (market_name);
CREATE INDEX ON traffic.event_type (event_type);

CREATE INDEX ON traffic.schedule (id);
CREATE INDEX ON traffic.schedule (team_name);
CREATE INDEX ON traffic.schedule (event_market);
CREATE INDEX ON traffic.schedule (location_name);