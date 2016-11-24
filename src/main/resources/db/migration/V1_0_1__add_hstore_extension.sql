CREATE EXTENSION hstore;

DROP TABLE IF EXISTS teams;
CREATE TABLE teams (
  id             SERIAL4 PRIMARY KEY,
  team_name      VARCHAR(100),
  training_times hstore
)