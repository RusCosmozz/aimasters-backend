package ru.dungeon.aimasters.backend.services;

import java.util.UUID;
import ru.dungeon.aimasters.backend.dtos.world.WorldRequestDto;
import ru.dungeon.aimasters.backend.dtos.world.WorldResponseDto;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
public interface WorldService {

  WorldResponseDto createWorld(WorldRequestDto worldRequestDto, UUID gameId);

  WorldResponseDto findWorldById(UUID id);
  WorldResponseDto findWorldByGameId(UUID id);
}
