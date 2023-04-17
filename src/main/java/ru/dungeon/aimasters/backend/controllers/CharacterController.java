package ru.dungeon.aimasters.backend.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dungeon.aimasters.backend.domain.dtos.character.CharacterRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.character.CharacterResponseDto;
import ru.dungeon.aimasters.backend.security.token.JwtTokenService;
import ru.dungeon.aimasters.backend.services.CharacterService;

import java.util.List;
import java.util.UUID;

/**
 * @author Ermakov KS
 * @since 06.04.2023
 */
@Slf4j
@RestController
@RequestMapping("/api/worlds/{worldId}/characters")
@AllArgsConstructor
@Api(tags = "Characters")
public class CharacterController {

    private final CharacterService characterService;

    private final JwtTokenService jwtTokenService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Создание персонажа")
    public CharacterResponseDto createCharacter(
            @RequestBody CharacterRequestDto characterRequestDto,
            @PathVariable UUID worldId) {

        return characterService.saveCharacter(characterRequestDto,worldId);
    }

}
