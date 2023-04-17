SET search_path TO aimasters;

CREATE TABLE IF NOT EXISTS characters
(
    id             UUID         NOT NULL,
    world_id       UUID         NOT NULL,
    name           VARCHAR(255) NOT NULL,
    race           VARCHAR(255) NOT NULL,
    race_overview  TEXT         NOT NULL,
    class          VARCHAR(255) NOT NULL,
    class_overview TEXT         NOT NULL,
    gender         VARCHAR(255) NOT NULL,
    level          INTEGER      NOT NULL,
    backstory      TEXT,
    PRIMARY KEY (id),
    CONSTRAINT fk_characters_world_id foreign key (world_id) REFERENCES worlds (id)
);

GRANT ALL PRIVILEGES on characters to aimasters_app;