package ru.dungeon.aimasters.backend.repositories;

import org.springframework.stereotype.Repository;
import ru.dungeon.aimasters.backend.domain.entities.GameSession;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Repository
public interface GameSessionRepository extends BaseUUIDRepository<GameSession> {

}
