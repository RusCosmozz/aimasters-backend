package ru.dungeon.aimasters.backend.controllers;

import java.util.UUID;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dungeon.aimasters.backend.domain.dtos.world.WorldDetailsResponseDto;
import ru.dungeon.aimasters.backend.domain.dtos.world.WorldRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.world.WorldResponseDto;
import ru.dungeon.aimasters.backend.services.WorldService;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Slf4j
@RestController
@RequestMapping("/api/lobbies/{lobbyId}/worlds")
@AllArgsConstructor
@Api(tags = "Worlds")
public class WorldController {

    private final WorldService worldService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Создание мира")
    public WorldResponseDto createWorld(
            @RequestBody WorldRequestDto worldRequestDto,
            @PathVariable UUID lobbyId) {

        return worldService.createWorld(worldRequestDto, lobbyId);
    }

    @GetMapping("/{worldId}")
    @ApiOperation("Получение информации о мире (персонажи и истории)")
    public WorldDetailsResponseDto getWorldDetails(
            @PathVariable UUID lobbyId,
            @PathVariable UUID worldId) {

        return worldService.getWorldDetails(lobbyId, worldId);
    }

}
