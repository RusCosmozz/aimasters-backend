package ru.dungeon.aimasters.backend.services;

import javax.servlet.http.HttpSession;
import ru.dungeon.aimasters.backend.domain.entities.World;
import ru.dungeon.aimasters.backend.dtos.ai.AiResponseDto;

/**
 * @author Ermakov KS
 * @since 05.04.2023
 */
public interface AiService {

  AiResponseDto generateWorld(HttpSession httpSession);

  AiResponseDto createCharacter(HttpSession httpSession);

  AiResponseDto startGameSession(HttpSession httpSession);
}
