package ru.dungeon.aimasters.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.dungeon.aimasters.backend.domain.dtos.story.StoryRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.story.StoryResponseDto;
import ru.dungeon.aimasters.backend.domain.dtos.world.WorldRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.world.WorldResponseDto;
import ru.dungeon.aimasters.backend.domain.entities.Story;
import ru.dungeon.aimasters.backend.domain.entities.World;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Mapper(componentModel = "spring")
public interface StoryMapper {

  @Mapping(target = "world", ignore = true)
  @Mapping(target = "status", constant = "CREATED")
  Story toStoryEntity(StoryRequestDto storyRequestDto);

  @Mapping(target = "worldId", source = "world.id")
  StoryResponseDto toStoryDto(Story story);

}
