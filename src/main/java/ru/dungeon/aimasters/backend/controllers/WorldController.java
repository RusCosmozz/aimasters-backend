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
@RequestMapping("/api/lobbies/{lobbyId}/worlds")
@AllArgsConstructor
public class WorldController {

  private final WorldService worldService;
  private final AiService aiService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public WorldResponseDto createWorld(
      @PathVariable UUID lobbyId,
      @RequestBody WorldRequestDto worldRequestDto) {

    return worldService.createWorld(worldRequestDto, lobbyId);
  }

}
