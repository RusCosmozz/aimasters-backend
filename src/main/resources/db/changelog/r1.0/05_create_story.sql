SET search_path TO aimasters;

CREATE TABLE if not exists stories
(
    id          UUID         NOT NULL,
    world_id    UUID         NOT NULL,
    title       VARCHAR(255) NOT NULL,
    description TEXT         NOT NULL,
    status      VARCHAR(255) NOT NULL,
    PRIMARY KEY (id),
    CONSTRAINT fk_stories_world_id foreign key (world_id) REFERENCES worlds (id)
);

CREATE TABLE IF NOT EXISTS character_story
(
    character_id UUID NOT NULL,
    story_id     UUID NOT NULL,
    PRIMARY KEY (character_id, story_id),
    CONSTRAINT fk_character_story_character_id foreign key (character_id) REFERENCES characters (id),
    CONSTRAINT fk_character_story_story_id foreign key (story_id) REFERENCES stories (id)
);