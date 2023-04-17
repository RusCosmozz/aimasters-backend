package ru.dungeon.aimasters.backend.services.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dungeon.aimasters.backend.domain.entities.Lobby;
import ru.dungeon.aimasters.backend.domain.entities.User;
import ru.dungeon.aimasters.backend.domain.entities.World;
import ru.dungeon.aimasters.backend.exceptions.exceptions.EntityNotFoundException;
import ru.dungeon.aimasters.backend.repositories.CharacterRepository;
import ru.dungeon.aimasters.backend.repositories.LobbyRepository;
import ru.dungeon.aimasters.backend.repositories.UserRepository;
import ru.dungeon.aimasters.backend.repositories.WorldRepository;
import ru.dungeon.aimasters.backend.services.CommonService;

import java.util.UUID;

@Service
@AllArgsConstructor
public class CommonServiceImpl implements CommonService {
    private final WorldRepository worldRepository;
    private final UserRepository userRepository;
    private final LobbyRepository lobbyRepository;
    private final CharacterRepository characterRepository;

    @Override
    public World getWorldIfExists(UUID worldId) {
        return worldRepository.findById(worldId)
                .orElseThrow(() -> new EntityNotFoundException("World not found with ID: " + worldId));
    }

    @Override
    public User getUsersByIdIfExists(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
    }

    @Override
    public Lobby getLobbyIfExists(UUID lobbyId) {
        return lobbyRepository.findById(lobbyId)
                .orElseThrow(() -> new EntityNotFoundException("Lobby not found with ID: " + lobbyId));
    }

    @Override
    public void checkIfWorldExists(UUID worldId) {
        if (!worldRepository.existsById(worldId)) {
            throw new EntityNotFoundException("World not found with ID: " + worldId);
        }
    }

    @Override
    public void checkIfLobbyExists(UUID lobbyId) {
        if (!lobbyRepository.existsById(lobbyId)) {
            throw new EntityNotFoundException("Lobby not found with ID: " + lobbyId);
        }
    }

    @Override
    public void checkIfCharacterExists(UUID charId) {
        if (!characterRepository.existsById(charId)) {
            throw new EntityNotFoundException("Character not found with ID: " + charId);
        }
    }
}
