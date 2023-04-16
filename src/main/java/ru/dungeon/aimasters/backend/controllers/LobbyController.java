package ru.dungeon.aimasters.backend.controllers;

import java.util.List;
import java.util.UUID;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dungeon.aimasters.backend.domain.dtos.lobby.CompactLobbyResponseDto;
import ru.dungeon.aimasters.backend.domain.dtos.lobby.LobbyRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.lobby.LobbyResponseDto;
import ru.dungeon.aimasters.backend.security.token.JwtTokenService;
import ru.dungeon.aimasters.backend.services.LobbyService;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Slf4j
@RestController
@RequestMapping("/api/lobbies")
@AllArgsConstructor
@Api(tags = "Lobbies")
public class LobbyController {

    private final LobbyService lobbyService;

    private final JwtTokenService jwtTokenService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Создание лобби")
    public LobbyResponseDto createLobby(
            @RequestBody LobbyRequestDto lobbyRequestDto,
            @RequestHeader("Authorization") String authorizationHeader) {

        UUID userId = jwtTokenService.getIdFromJwt(authorizationHeader);
        return lobbyService.createLobby(lobbyRequestDto, userId);
    }

    @GetMapping
    @ApiOperation("Получение списка всех лобби")
    public List<CompactLobbyResponseDto> getAllLobbies() {
        return lobbyService.getAllLobbies();
    }

    @GetMapping("/{lobbyId}")
    @ApiOperation("Получение лобби по айди")
    public LobbyResponseDto getLobbyById(@PathVariable UUID lobbyId) {
        return lobbyService.getLobbyById(lobbyId);
    }
}
