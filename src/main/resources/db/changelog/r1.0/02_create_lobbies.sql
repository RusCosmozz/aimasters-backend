SET search_path TO aimasters;

CREATE TABLE IF NOT EXISTS lobbies
(
    id      UUID         NOT NULL,
    host_id UUID         NOT NULL,
    name    VARCHAR(255) NOT NULL,
    status  VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_lobbies_user_id foreign key (host_id) REFERENCES users (id)
);

GRANT ALL PRIVILEGES on lobbies to aimasters_app;