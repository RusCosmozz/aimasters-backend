package ru.dungeon.aimasters.backend.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import ru.dungeon.aimasters.backend.domain.dtos.character.CharacterResponseDto;
import ru.dungeon.aimasters.backend.domain.dtos.story.StoryResponseDto;
import ru.dungeon.aimasters.backend.domain.dtos.world.WorldDetailsResponseDto;
import ru.dungeon.aimasters.backend.domain.entities.Lobby;
import ru.dungeon.aimasters.backend.domain.entities.World;
import ru.dungeon.aimasters.backend.domain.dtos.world.WorldRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.world.WorldResponseDto;
import ru.dungeon.aimasters.backend.exceptions.exceptions.EntityNotFoundException;
import ru.dungeon.aimasters.backend.mappers.WorldMapper;
import ru.dungeon.aimasters.backend.repositories.LobbyRepository;
import ru.dungeon.aimasters.backend.repositories.WorldRepository;
import ru.dungeon.aimasters.backend.services.CharacterService;
import ru.dungeon.aimasters.backend.services.CommonService;
import ru.dungeon.aimasters.backend.services.StoryService;
import ru.dungeon.aimasters.backend.services.WorldService;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class WorldServiceImpl implements WorldService {

    private final WorldRepository worldRepository;
    private final CommonService commonService;
    private final CharacterService characterService;
    private final StoryService storyService;
    private final WorldMapper worldMapper;

    @Override
    @Transactional
    public WorldResponseDto createWorld(WorldRequestDto worldRequestDto, UUID lobbyId) {
        log.info("сохранение мира для лобби {}",lobbyId);
        Lobby lobby = commonService.getLobbyIfExists(lobbyId);
        log.info("лобби {} найдено",lobbyId);

        World world = worldMapper.toWorldEntity(worldRequestDto);
        world.setLobby(lobby);
        World savedWorld = worldRepository.save(world);
        log.info("мир {} сохранен успешно",savedWorld.getWorldName());

        return worldMapper.toWorldDto(savedWorld);
    }

    @Override
    public WorldDetailsResponseDto getWorldDetails(UUID lobbyId, UUID worldId) {
        commonService.checkIfLobbyExists(lobbyId);
        World world = commonService.getWorldIfExists(worldId);
        WorldDetailsResponseDto worldDetails = worldMapper.toWorldDetailsResponseDto(world);

        List<CharacterResponseDto> characters = characterService.getCharactersByWorldId(worldId);
        worldDetails.setCharacters(characters);

        List<StoryResponseDto> stories = storyService.getStoriesByWorldId(worldId);
        worldDetails.setStories(stories);

        return worldDetails;
    }
}
