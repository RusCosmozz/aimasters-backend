SET search_path TO aimasters;

CREATE TABLE IF NOT EXISTS worlds
(
    id          UUID         NOT NULL,
    lobby_id    UUID         NOT NULL,
    world_name  VARCHAR(255) NOT NULL,
    description TEXT         NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_worlds_lobby_id foreign key (lobby_id) REFERENCES lobbies (id)
);

GRANT ALL PRIVILEGES on worlds to aimasters_app;