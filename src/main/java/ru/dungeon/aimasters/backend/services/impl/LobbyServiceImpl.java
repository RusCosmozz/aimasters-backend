package ru.dungeon.aimasters.backend.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dungeon.aimasters.backend.domain.entities.Lobby;
import ru.dungeon.aimasters.backend.domain.entities.User;
import ru.dungeon.aimasters.backend.dtos.session.CompactLobbyResponseDto;
import ru.dungeon.aimasters.backend.dtos.session.LobbyRequestDto;
import ru.dungeon.aimasters.backend.dtos.session.LobbyResponseDto;
import ru.dungeon.aimasters.backend.exceptions.exceptions.EntityNotFoundException;
import ru.dungeon.aimasters.backend.mappers.LobbyMapper;
import ru.dungeon.aimasters.backend.repositories.LobbyRepository;
import ru.dungeon.aimasters.backend.repositories.UserRepository;
import ru.dungeon.aimasters.backend.repositories.WorldRepository;
import ru.dungeon.aimasters.backend.services.LobbyService;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class LobbyServiceImpl implements LobbyService {

  private UserRepository userRepository;
  private LobbyRepository lobbyRepository;
  private LobbyMapper lobbyMapper;

  @Override
  @Transactional
  public LobbyResponseDto createLobby(LobbyRequestDto lobbyRequestDto, UUID hostId) {
    User host = getHostIfExists(hostId);
    Lobby lobbyEntity = lobbyMapper.toLobbyEntity(lobbyRequestDto);
    lobbyEntity.setHost(host);
    Lobby savedLobby = lobbyRepository.save(lobbyEntity);
    return lobbyMapper.toLobbyResponseDto(savedLobby);
  }


  @Override
  public List<CompactLobbyResponseDto> getAllLobbies() {
    List<Lobby> lobbies = lobbyRepository.findAll();
    return lobbies.stream().map(lobbyMapper::toCompactLobbyResponseDto).collect(Collectors.toList());
  }

  private User getHostIfExists(UUID userId) {
    return userRepository.findById(userId)
                         .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
  }


}
