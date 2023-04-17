package ru.dungeon.aimasters.backend.repositories;

import ru.dungeon.aimasters.backend.domain.entities.Story;

import java.util.List;
import java.util.UUID;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
public interface StoryRepository extends BaseUUIDRepository<Story> {
    List<Story> findAllByWorldId(UUID worldId);

}
