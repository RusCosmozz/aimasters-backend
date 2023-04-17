package ru.dungeon.aimasters.backend.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.dungeon.aimasters.backend.domain.entities.PlayableCharacter;
import ru.dungeon.aimasters.backend.domain.entities.User;
import ru.dungeon.aimasters.backend.domain.entities.World;
import ru.dungeon.aimasters.backend.domain.dtos.character.CharacterRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.character.CharacterResponseDto;
import ru.dungeon.aimasters.backend.exceptions.exceptions.EntityNotFoundException;
import ru.dungeon.aimasters.backend.mappers.CharacterMapper;
import ru.dungeon.aimasters.backend.repositories.CharacterRepository;
import ru.dungeon.aimasters.backend.repositories.UserRepository;
import ru.dungeon.aimasters.backend.repositories.WorldRepository;
import ru.dungeon.aimasters.backend.services.CharacterService;
import ru.dungeon.aimasters.backend.services.CommonService;

/**
 * @author Ermakov KS
 * @since 06.04.2023
 */
@Slf4j
@Service
@AllArgsConstructor
public class CharacterServiceImpl implements CharacterService {

  private final CharacterRepository characterRepository;
  private final CommonService commonService;
  private final CharacterMapper characterMapper;

  @Override
  public CharacterResponseDto saveCharacter(CharacterRequestDto characterRequestDto, UUID worldId) {
    log.info("сохранение персонажа для мира {}",worldId);
    World world = commonService.getWorldIfExists(worldId);
    log.info("мир {} найден",worldId);

    PlayableCharacter character = characterMapper.toCharacterEntity(characterRequestDto);
    character.setWorld(world);
    PlayableCharacter savedCharacter = characterRepository.save(character);
    log.info("персонаж {} сохранен успешно",savedCharacter.getId());

    return characterMapper.toCharacterDto(savedCharacter);
  }

  @Override
  public List<CharacterResponseDto> getCharactersByWorldId(UUID worldId) {
    log.info("Поиск персонажей для мира {}",worldId);
    List<PlayableCharacter> characters = characterRepository.findAllByWorldId(worldId);
    log.info("найдено {} персонажей",characters.size());
    return characters.stream()
            .map(characterMapper::toCharacterDto)
            .collect(Collectors.toList());
  }
}
