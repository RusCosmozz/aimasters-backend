package ru.dungeon.aimasters.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.dungeon.aimasters.backend.domain.entities.PlayableCharacter;
import ru.dungeon.aimasters.backend.domain.dtos.character.CharacterRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.character.CharacterResponseDto;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Mapper(componentModel = "spring")
public interface CharacterMapper {

  @Mapping(target = "user", ignore = true)
  @Mapping(target = "world", ignore = true)
  @Mapping(target = "level", constant = "1")
  PlayableCharacter toCharacterEntity(CharacterRequestDto characterRequestDto);

  @Mapping(target = "userId", source = "user.id")
  @Mapping(target = "worldId", source = "world.id")
  CharacterResponseDto toCharacterDto(PlayableCharacter characterRequestDto);

}
