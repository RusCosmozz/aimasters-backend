package ru.dungeon.aimasters.backend.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dungeon.aimasters.backend.domain.entities.Lobby;
import ru.dungeon.aimasters.backend.domain.entities.World;
import ru.dungeon.aimasters.backend.domain.dtos.world.WorldRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.world.WorldResponseDto;
import ru.dungeon.aimasters.backend.exceptions.exceptions.EntityNotFoundException;
import ru.dungeon.aimasters.backend.mappers.WorldMapper;
import ru.dungeon.aimasters.backend.repositories.LobbyRepository;
import ru.dungeon.aimasters.backend.repositories.WorldRepository;
import ru.dungeon.aimasters.backend.services.WorldService;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class WorldServiceImpl implements WorldService {

    private final WorldRepository worldRepository;
    private final LobbyRepository lobbyRepository;
    private final WorldMapper worldMapper;

    @Override
    @Transactional
    public WorldResponseDto createWorld(WorldRequestDto worldRequestDto, UUID gameId) {
        Lobby lobby = getLobbyIfExists(gameId);
        World world = worldMapper.toWorldEntity(worldRequestDto);
        world.setLobby(lobby);
        World savedWorld = worldRepository.save(world);
        return worldMapper.toWorldDto(savedWorld);
    }

    @Override
    public WorldResponseDto findWorldById(UUID id) {
        return null;
    }

    @Override
    public List<WorldResponseDto> findWorldsByGameId(UUID lobbyId) {
        return worldRepository.findAllByLobbyId(lobbyId)
                .stream()
                .map(worldMapper::toWorldDto)
                .collect(Collectors.toList());
    }


    private Lobby getLobbyIfExists(UUID gameId) {
        return lobbyRepository.findById(gameId)
                .orElseThrow(() -> new EntityNotFoundException("Game not found with ID: " + gameId));
    }

}
