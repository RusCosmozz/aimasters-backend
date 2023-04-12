package ru.dungeon.aimasters.backend.controllers;

import static ru.dungeon.aimasters.backend.utils.json.JsonUtils.fromJson;
import static ru.dungeon.aimasters.backend.utils.json.JsonUtils.toJson;

import java.util.UUID;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dungeon.aimasters.backend.dtos.ai.AiResponseDto;
import ru.dungeon.aimasters.backend.dtos.chat.MessageContent;
import ru.dungeon.aimasters.backend.dtos.world.WorldRequestDto;
import ru.dungeon.aimasters.backend.dtos.world.WorldResponseDto;
import ru.dungeon.aimasters.backend.services.AiService;
import ru.dungeon.aimasters.backend.services.WorldService;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Slf4j
@RestController
@RequestMapping("/api/game-sessions/{gameId}/worlds")
@AllArgsConstructor
public class WorldController {

  private final WorldService worldService;
  private final AiService aiService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public WorldResponseDto createWorld(
      @PathVariable UUID gameId,
      @RequestBody WorldRequestDto worldRequestDto) {

    log.info("{}",worldRequestDto);
//    log.info("Запрос на генерацию нового мира");
//    AiResponseDto aiResponseDto = aiService.generateWorld(httpSession);
//    MessageContent messageContent = aiResponseDto.getChoices().get(0).getMessageContent();
//    log.info("{}", toJson(messageContent));
//    WorldRequestDto worldRequestDto = fromJson(messageContent.getMessage(), WorldRequestDto.class);
    return worldService.createWorld(worldRequestDto, gameId);
  }

  @GetMapping("/{worldId}")
  public WorldResponseDto getWorldById(@PathVariable UUID gameId, @PathVariable UUID worldId) {
    return worldService.findWorldById(worldId);
  }

  @GetMapping
  public WorldResponseDto getWorldByGameId(@PathVariable UUID gameId) {
    return worldService.findWorldByGameId(gameId);
  }
}
