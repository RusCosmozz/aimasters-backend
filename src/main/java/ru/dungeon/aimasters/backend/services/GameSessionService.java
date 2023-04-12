package ru.dungeon.aimasters.backend.services;

import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import ru.dungeon.aimasters.backend.dtos.session.GameSessionRequestDto;
import ru.dungeon.aimasters.backend.dtos.session.GameSessionResponseDto;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
public interface GameSessionService {

  GameSessionResponseDto createGameSession(
      GameSessionRequestDto gameSessionRequestDto,
      UUID hostId,
      HttpSession session);

  GameSessionResponseDto findGameSessionById(UUID id, UUID hostId);
  List<GameSessionResponseDto> findAllGameSessions();
}
