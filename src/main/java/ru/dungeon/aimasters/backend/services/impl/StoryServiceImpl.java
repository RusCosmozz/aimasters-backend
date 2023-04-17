package ru.dungeon.aimasters.backend.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dungeon.aimasters.backend.domain.dtos.story.StoryRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.story.StoryResponseDto;
import ru.dungeon.aimasters.backend.domain.entities.PlayableCharacter;
import ru.dungeon.aimasters.backend.domain.entities.Story;
import ru.dungeon.aimasters.backend.domain.entities.World;
import ru.dungeon.aimasters.backend.mappers.StoryMapper;
import ru.dungeon.aimasters.backend.mappers.WorldMapper;
import ru.dungeon.aimasters.backend.repositories.LobbyRepository;
import ru.dungeon.aimasters.backend.repositories.StoryRepository;
import ru.dungeon.aimasters.backend.repositories.WorldRepository;
import ru.dungeon.aimasters.backend.services.CommonService;
import ru.dungeon.aimasters.backend.services.StoryService;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class StoryServiceImpl implements StoryService {

    private final StoryRepository storyRepository;
    private final StoryMapper storyMapper;
    private final CommonService commonService;

    @Override
    @Transactional
    public StoryResponseDto createStory(StoryRequestDto storyRequestDto, UUID worldId) {
        log.info("Создание новой истории в мире {}", worldId);
        World world = commonService.getWorldIfExists(worldId);
        log.info("Мир {} найден", worldId);
        storyRequestDto.getCharacters().forEach(commonService::checkIfCharacterExists);
        log.info("Персонажи найдены");
        Story story = storyMapper.toStoryEntity(storyRequestDto);
        story.setWorld(world);
        Story savedStory = storyRepository.save(story);
        log.info("История \"{}\" сохранена", savedStory.getTitle());

        return storyMapper.toStoryDto(savedStory);
    }

    @Override
    public List<StoryResponseDto> getStoriesByWorldId(UUID worldId) {
        log.info("Поиск историй для мира {}", worldId);
        List<Story> stories = storyRepository.findAllByWorldId(worldId);
        log.info("Найдено {} историй", stories.size());
        return stories.stream()
                .map(storyMapper::toStoryDto)
                .collect(Collectors.toList());
    }
}
