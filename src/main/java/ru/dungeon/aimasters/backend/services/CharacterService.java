package ru.dungeon.aimasters.backend.services;

import java.util.List;
import java.util.UUID;
import ru.dungeon.aimasters.backend.domain.dtos.character.CharacterRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.character.CharacterResponseDto;

/**
 * @author Ermakov KS
 * @since 06.04.2023
 */
public interface CharacterService {

  CharacterResponseDto saveCharacter(CharacterRequestDto character, UUID worldId);
  List<CharacterResponseDto> getCharactersByWorldId(UUID worldId);
}
