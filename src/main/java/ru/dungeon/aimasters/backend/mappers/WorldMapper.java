package ru.dungeon.aimasters.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.dungeon.aimasters.backend.domain.dtos.character.CharacterResponseDto;
import ru.dungeon.aimasters.backend.domain.dtos.story.StoryResponseDto;
import ru.dungeon.aimasters.backend.domain.dtos.world.WorldDetailsResponseDto;
import ru.dungeon.aimasters.backend.domain.entities.World;
import ru.dungeon.aimasters.backend.domain.dtos.world.WorldRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.world.WorldResponseDto;

import java.util.List;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Mapper(componentModel = "spring")
public interface WorldMapper {

  @Mapping(target = "lobby", ignore = true)
  World toWorldEntity(WorldRequestDto worldRequestDto);

  @Mapping(target = "lobbyId", source = "lobby.id")
  WorldResponseDto toWorldDto(World world);

  WorldDetailsResponseDto toWorldDetailsResponseDto(World world);
}
