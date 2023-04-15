SET search_path TO aimasters;

CREATE TABLE IF NOT EXISTS users
(
    id       UUID                NOT NULL,
    username VARCHAR(255) UNIQUE NOT NULL,
    email    VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255)        NOT NULL,
    roles    VARCHAR(255)        NOT NULL,
    PRIMARY KEY (id)
);

GRANT ALL PRIVILEGES on users to aimasters_app;