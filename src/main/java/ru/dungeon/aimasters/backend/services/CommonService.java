package ru.dungeon.aimasters.backend.services;

import ru.dungeon.aimasters.backend.domain.entities.Lobby;
import ru.dungeon.aimasters.backend.domain.entities.User;
import ru.dungeon.aimasters.backend.domain.entities.World;

import java.util.UUID;

public interface CommonService {
    World getWorldIfExists(UUID worldId);
    User getUsersByIdIfExists(UUID userId);
    Lobby getLobbyIfExists(UUID lobbyId);

    void checkIfWorldExists(UUID worldId);
    void checkIfCharacterExists(UUID charId);
    void checkIfLobbyExists(UUID lobbyId);
}
