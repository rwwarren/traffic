CREATE TABLE traffic.teams_per_city(
  id BIGSERIAL,
  city_name VARCHAR(255) NOT NULL,
  team_info_id BIGINT REFERENCES traffic.team_info(id)
);