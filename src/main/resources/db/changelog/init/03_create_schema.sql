CREATE SCHEMA IF NOT EXISTS aimasters AUTHORIZATION aimaster_owner;

GRANT USAGE ON SCHEMA aimasters TO aimasters_app;
GRANT ALL ON SCHEMA aimasters TO aimasters_app;

ALTER USER master SET search_path = aimasters, information_schema;