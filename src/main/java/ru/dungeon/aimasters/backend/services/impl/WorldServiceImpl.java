package ru.dungeon.aimasters.backend.services.impl;

import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dungeon.aimasters.backend.domain.entities.GameSession;
import ru.dungeon.aimasters.backend.domain.entities.World;
import ru.dungeon.aimasters.backend.dtos.world.WorldRequestDto;
import ru.dungeon.aimasters.backend.dtos.world.WorldResponseDto;
import ru.dungeon.aimasters.backend.exceptions.exceptions.EntityNotFoundException;
import ru.dungeon.aimasters.backend.mappers.WorldMapper;
import ru.dungeon.aimasters.backend.repositories.GameSessionRepository;
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
  private final GameSessionRepository gameSessionRepository;
  private final WorldMapper worldMapper;

  @Override
  @Transactional
  public WorldResponseDto createWorld(WorldRequestDto worldRequestDto, UUID gameId) {
    GameSession gameSession = getGameIfExists(gameId);
    World world = worldMapper.toWorldEntity(worldRequestDto);
    world.setGameSession(gameSession);
    World savedWorld = worldRepository.save(world);
    return worldMapper.toWorldDto(savedWorld);
  }

  @Override
  public WorldResponseDto findWorldById(UUID id) {
    World world = worldRepository.findById(id)
                                 .orElseThrow(() -> new EntityNotFoundException("World not found with ID: " + id));
    return worldMapper.toWorldDto(world);
  }

  @Override
  public WorldResponseDto findWorldByGameId(UUID id) {
    World world = worldRepository.findWorldByGameSessionId(id)
            .orElseThrow(() -> new EntityNotFoundException("World not found with ID: " + id));
    return worldMapper.toWorldDto(world);
  }

  private GameSession getGameIfExists(UUID gameId) {
    return gameSessionRepository.findById(gameId)
                                .orElseThrow(() -> new EntityNotFoundException("Game not found with ID: " + gameId));
  }
}
