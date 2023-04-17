package ru.dungeon.aimasters.backend.services;

import ru.dungeon.aimasters.backend.domain.dtos.world.WorldDetailsResponseDto;
import ru.dungeon.aimasters.backend.domain.dtos.world.WorldRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.world.WorldResponseDto;

import java.util.UUID;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
public interface WorldService {

    WorldResponseDto createWorld(WorldRequestDto worldRequestDto, UUID lobbyId);

    WorldDetailsResponseDto getWorldDetails(UUID lobbyId, UUID worldId);
}
