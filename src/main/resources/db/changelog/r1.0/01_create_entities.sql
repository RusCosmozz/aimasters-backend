
--todo
CREATE TABLE if not exists stories
(
    id              UUID         NOT NULL,
    game_session_id UUID REFERENCES game_sessions (id),
    world_id        UUID REFERENCES worlds (id),
    title           VARCHAR(255) NOT NULL,
    description     TEXT         NOT NULL,
    status          VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);
