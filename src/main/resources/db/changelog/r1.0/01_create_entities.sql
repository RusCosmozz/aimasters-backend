SET search_path TO aimasters;

CREATE TABLE if not exists users
(
    id       UUID                NOT NULL,
    username VARCHAR(255)        NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    roles    VARCHAR(255)        NOT NULL,
    PRIMARY KEY (id)
);


CREATE TABLE if not exists game_sessions
(
    id      UUID         NOT NULL,
    host_id UUID REFERENCES users (id),
    summary TEXT,
    name  VARCHAR(255),
    status  VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE if not exists worlds
(
    id              UUID NOT NULL,
    world_name      VARCHAR(255),
    description     TEXT,
    game_session_id uuid REFERENCES game_sessions (id),
    PRIMARY KEY (id)
);

CREATE TABLE if not exists characters
(
    id             UUID         NOT NULL,
    user_id        UUID REFERENCES users (id),
    world_id       UUID REFERENCES worlds (id),
    name           VARCHAR(255) NOT NULL,
    race           VARCHAR(255) NOT NULL,
    race_overview  TEXT         NOT NULL,
    class          VARCHAR(255) NOT NULL,
    class_overview TEXT         NOT NULL,
    gender         VARCHAR(255) NOT NULL,
    level          INTEGER      NOT NULL,
--     attributes     JSON         NOT NULL,
    backstory      TEXT,
    PRIMARY KEY (id)
);

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

--todo индексы и гранты