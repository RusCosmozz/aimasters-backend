CREATE SCHEMA IF NOT EXISTS ext;

GRANT USAGE ON SCHEMA ext TO aimaster_owner;
GRANT USAGE ON SCHEMA ext TO aimasters_app;

CREATE EXTENSION IF NOT EXISTS pgcrypto WITH SCHEMA ext;