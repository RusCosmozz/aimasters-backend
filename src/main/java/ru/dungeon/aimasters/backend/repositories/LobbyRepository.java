package ru.dungeon.aimasters.backend.repositories;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.stereotype.Repository;
import ru.dungeon.aimasters.backend.domain.entities.Lobby;

import java.util.Optional;
import java.util.UUID;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Repository
public interface LobbyRepository extends BaseUUIDRepository<Lobby> {
    @EntityGraph("lobby-with-worlds")
    Optional<Lobby> findWithWorldsById(UUID lobbyId);
}
