package ru.dungeon.aimasters.backend.controllers;

import static ru.dungeon.aimasters.backend.utils.json.JsonUtils.toJson;

import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpSession;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.dungeon.aimasters.backend.dtos.ai.AiResponseDto;
import ru.dungeon.aimasters.backend.dtos.chat.MessageContent;
import ru.dungeon.aimasters.backend.dtos.session.GameSessionRequestDto;
import ru.dungeon.aimasters.backend.dtos.session.GameSessionResponseDto;
import ru.dungeon.aimasters.backend.services.AiService;
import ru.dungeon.aimasters.backend.services.GameSessionService;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Slf4j
@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class GameSessionController {

    private final GameSessionService gameSessionService;
    private final AiService aiService;

    @PostMapping("/users/{userId}/game-sessions")
    @ResponseStatus(HttpStatus.CREATED)
    public GameSessionResponseDto createGameSession(
            @PathVariable UUID userId,
            @RequestBody GameSessionRequestDto gameSessionRequestDto,
            HttpSession httpSession) {

        log.info("{}",gameSessionRequestDto);

        log.info("Запрос на старт игровой сессии");
//        AiResponseDto aiResponseDto = aiService.startGameSession(httpSession);
//        MessageContent messageContent = aiResponseDto.getChoices().get(0).getMessageContent();
//        log.info("{}", toJson(messageContent));
        return gameSessionService.createGameSession(gameSessionRequestDto, userId, httpSession);
    }

    @GetMapping("/game-sessions/{gameSessionId}")
    public GameSessionResponseDto getGameSessionById(@PathVariable UUID userId, @PathVariable UUID gameSessionId) {
        return gameSessionService.findGameSessionById(gameSessionId, userId);
    }

    @GetMapping("/game-sessions")
    public List<GameSessionResponseDto> getAllGameSessions() {
        return gameSessionService.findAllGameSessions();
    }
}
