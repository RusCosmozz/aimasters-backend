package ru.dungeon.aimasters.backend.services;

import java.util.List;
import java.util.UUID;

import ru.dungeon.aimasters.backend.dtos.lobby.CompactLobbyResponseDto;
import ru.dungeon.aimasters.backend.dtos.lobby.LobbyRequestDto;
import ru.dungeon.aimasters.backend.dtos.lobby.LobbyResponseDto;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
public interface LobbyService {

  LobbyResponseDto createLobby(
      LobbyRequestDto lobbyRequestDto,
      UUID hostId);

  List<CompactLobbyResponseDto> getAllLobbies();

  LobbyResponseDto getLobbyById(UUID lobbyId);
}
