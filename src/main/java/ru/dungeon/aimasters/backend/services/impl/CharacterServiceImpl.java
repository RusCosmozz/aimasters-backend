package ru.dungeon.aimasters.backend.services.impl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.dungeon.aimasters.backend.domain.entities.PlayableCharacter;
import ru.dungeon.aimasters.backend.domain.entities.User;
import ru.dungeon.aimasters.backend.domain.entities.World;
import ru.dungeon.aimasters.backend.dtos.character.CharacterRequestDto;
import ru.dungeon.aimasters.backend.dtos.character.CharacterResponseDto;
import ru.dungeon.aimasters.backend.exceptions.exceptions.EntityNotFoundException;
import ru.dungeon.aimasters.backend.mappers.CharacterMapper;
import ru.dungeon.aimasters.backend.repositories.CharacterRepository;
import ru.dungeon.aimasters.backend.repositories.UserRepository;
import ru.dungeon.aimasters.backend.repositories.WorldRepository;
import ru.dungeon.aimasters.backend.services.CharacterService;

/**
 * @author Ermakov KS
 * @since 06.04.2023
 */
@Service
@AllArgsConstructor
public class CharacterServiceImpl implements CharacterService {

  private final CharacterRepository characterRepository;
  private final UserRepository userRepository;
  private final WorldRepository worldRepository;
  private final CharacterMapper characterMapper;

  @Override
  public CharacterResponseDto savePlayerCharacter(CharacterRequestDto characterRequestDto, UUID userId, UUID worldId) {
//    User user = getUserIfExists(userId);
    World world = getWorldIfExists(worldId);
    PlayableCharacter character = characterMapper.toCharacterEntity(characterRequestDto);
//    character.setUser(user);
    character.setWorld(world);
    PlayableCharacter savedCharacter = characterRepository.save(character);
    return characterMapper.toCharacterDto(savedCharacter);
  }

  @Override
  public List<CharacterResponseDto> getPlayerCharByWorldId(UUID worldId) {
    getWorldIfExists(worldId);
    return characterRepository.findAllByWorldId(worldId).stream().map(characterMapper::toCharacterDto).collect(Collectors.toList());
  }

  private User getUserIfExists(UUID userId) {
    return userRepository.findById(userId)
                         .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + userId));
  }

  private World getWorldIfExists(UUID worldId) {
    return worldRepository.findById(worldId)
                          .orElseThrow(() -> new EntityNotFoundException("User not found with ID: " + worldId));
  }
}
