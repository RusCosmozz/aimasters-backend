package ru.dungeon.aimasters.backend.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dungeon.aimasters.backend.domain.entities.GameSession;
import ru.dungeon.aimasters.backend.domain.entities.User;
import ru.dungeon.aimasters.backend.domain.entities.World;
import ru.dungeon.aimasters.backend.dtos.ai.AiResponseDto;
import ru.dungeon.aimasters.backend.dtos.session.GameSessionRequestDto;
import ru.dungeon.aimasters.backend.dtos.session.GameSessionResponseDto;
import ru.dungeon.aimasters.backend.exceptions.exceptions.EntityNotFoundException;
import ru.dungeon.aimasters.backend.mappers.GameSessionMapper;
import ru.dungeon.aimasters.backend.repositories.GameSessionRepository;
import ru.dungeon.aimasters.backend.repositories.UserRepository;
import ru.dungeon.aimasters.backend.repositories.WorldRepository;
import ru.dungeon.aimasters.backend.services.AiService;
import ru.dungeon.aimasters.backend.services.GameSessionService;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class GameSessionServiceImpl implements GameSessionService {

  private UserRepository userRepository;
  private WorldRepository worldRepository;
  private GameSessionRepository gameSessionRepository;
  private GameSessionMapper gameSessionMapper;

  @Override
  @Transactional
  public GameSessionResponseDto createGameSession(
      GameSessionRequestDto gameSessionRequestDto,
      UUID hostId,
      HttpSession session) {

    User host = getHostIfExists(hostId);
    GameSession gameSessionEntity = gameSessionMapper.toGameSessionEntity(gameSessionRequestDto);
    gameSessionEntity.setHost(host);
    GameSession savedGameSession = gameSessionRepository.save(gameSessionEntity);
    return gameSessionMapper.toGameSessionResponseDto(savedGameSession);
  }

  @Override
  public GameSessionResponseDto findGameSessionById(UUID id, UUID hostId) {
    //todo
    return null;
  }

  @Override
  public List<GameSessionResponseDto> findAllGameSessions() {
    List<GameSession> sessions = gameSessionRepository.findAll();
    return sessions.stream().map(gameSessionMapper::toGameSessionResponseDto).collect(Collectors.toList());
  }

  private User getHostIfExists(UUID userId) {
    return userRepository.findById(userId)
                         .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
  }


}
