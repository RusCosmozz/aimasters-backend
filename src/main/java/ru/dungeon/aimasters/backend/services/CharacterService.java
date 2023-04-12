package ru.dungeon.aimasters.backend.services;

import java.util.List;
import java.util.UUID;
import ru.dungeon.aimasters.backend.dtos.character.CharacterRequestDto;
import ru.dungeon.aimasters.backend.dtos.character.CharacterResponseDto;

/**
 * @author Ermakov KS
 * @since 06.04.2023
 */
public interface CharacterService {

  CharacterResponseDto savePlayerCharacter(CharacterRequestDto character, UUID userId, UUID worldId);
  List<CharacterResponseDto> getPlayerCharByWorldId(UUID worldId);
}
