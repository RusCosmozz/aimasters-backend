package ru.dungeon.aimasters.backend.repositories;

import org.springframework.stereotype.Repository;
import ru.dungeon.aimasters.backend.domain.entities.World;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Repository
public interface WorldRepository extends BaseUUIDRepository<World> {
    Optional<World> findWorldByGameSessionId(UUID gameId);
}