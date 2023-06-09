package ru.dungeon.aimasters.backend.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dungeon.aimasters.backend.domain.entities.Lobby;
import ru.dungeon.aimasters.backend.domain.entities.User;
import ru.dungeon.aimasters.backend.domain.dtos.lobby.CompactLobbyResponseDto;
import ru.dungeon.aimasters.backend.domain.dtos.lobby.LobbyRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.lobby.LobbyResponseDto;
import ru.dungeon.aimasters.backend.exceptions.exceptions.EntityNotFoundException;
import ru.dungeon.aimasters.backend.mappers.LobbyMapper;
import ru.dungeon.aimasters.backend.repositories.LobbyRepository;
import ru.dungeon.aimasters.backend.repositories.UserRepository;
import ru.dungeon.aimasters.backend.services.CommonService;
import ru.dungeon.aimasters.backend.services.LobbyService;
import ru.dungeon.aimasters.backend.services.WorldService;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Service
@Slf4j
@AllArgsConstructor
@Transactional(readOnly = true)
public class LobbyServiceImpl implements LobbyService {

    private CommonService commonService;
    private LobbyRepository lobbyRepository;
    private LobbyMapper lobbyMapper;

    @Override
    @Transactional
    public LobbyResponseDto createLobby(LobbyRequestDto lobbyRequestDto, UUID hostId) {
        log.info("Сохранение лобби");
        User host = commonService.getUsersByIdIfExists(hostId);
        log.info("хост {} найден",hostId);
        Lobby lobbyEntity = lobbyMapper.toLobbyEntity(lobbyRequestDto);
        lobbyEntity.setHost(host);
        Lobby savedLobby = lobbyRepository.save(lobbyEntity);
        log.info("лобби {} сохранено",savedLobby.getId());
        return lobbyMapper.toLobbyResponseDto(savedLobby);
    }

    @Override
    public LobbyResponseDto getLobbyById(UUID lobbyId) {
        Lobby lobby = lobbyRepository.findWithWorldsById(lobbyId)
                .orElseThrow(() -> new EntityNotFoundException("Lobby not found with ID: " + lobbyId));
        log.info("Лобби {} найдено \n количество миров {}", lobbyId, lobby.getWorlds().size());
        return lobbyMapper.toLobbyResponseDto(lobby);
    }

    @Override
    public List<CompactLobbyResponseDto> getAllLobbies() {
        List<Lobby> lobbies = lobbyRepository.findAll();
        return lobbies.stream()
                .map(lobbyMapper::toCompactLobbyResponseDto)
                .collect(Collectors.toList());
    }


}
