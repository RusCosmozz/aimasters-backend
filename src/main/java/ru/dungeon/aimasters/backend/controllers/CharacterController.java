package ru.dungeon.aimasters.backend.controllers;

import static ru.dungeon.aimasters.backend.utils.json.JsonUtils.fromJson;
import static ru.dungeon.aimasters.backend.utils.json.JsonUtils.toJson;

import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dungeon.aimasters.backend.dtos.ai.AiResponseDto;
import ru.dungeon.aimasters.backend.dtos.character.CharacterRequestDto;
import ru.dungeon.aimasters.backend.dtos.character.CharacterResponseDto;
import ru.dungeon.aimasters.backend.dtos.chat.MessageContent;
import ru.dungeon.aimasters.backend.services.AiService;
import ru.dungeon.aimasters.backend.services.CharacterService;

/**
 * @author Ermakov KS
 * @since 06.04.2023
 */
@Slf4j
@RestController
//todo /users/{userId}
@RequestMapping("/api/worlds/{worldId}/characters")
@AllArgsConstructor
public class CharacterController {

  private final CharacterService characterService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public CharacterResponseDto createPlayerCharacter(
      @PathVariable UUID worldId,
      @RequestBody CharacterRequestDto characterRequestDto) {

    log.info("{}",characterRequestDto);
/*    log.info("Запрос на генерацию нового персонажа");
    AiResponseDto aiResponseDto = aiService.createCharacter(httpSession);
    MessageContent messageContent = aiResponseDto.getChoices().get(0).getMessageContent();
    log.info("{}", toJson(messageContent));

    String characterJson = messageContent.getMessage();*/
    return characterService.savePlayerCharacter(characterRequestDto, UUID.randomUUID(), worldId);
  }
  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<CharacterResponseDto> createPlayerCharacter(
      @PathVariable UUID worldId) {

    return characterService.getPlayerCharByWorldId(worldId);
  }
}
