package ru.dungeon.aimasters.backend.repositories;

import org.springframework.stereotype.Repository;
import ru.dungeon.aimasters.backend.domain.entities.PlayableCharacter;

import java.util.List;
import java.util.UUID;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Repository
public interface CharacterRepository extends BaseUUIDRepository<PlayableCharacter> {
    List<PlayableCharacter> findAllByWorldId(UUID worldId);
}