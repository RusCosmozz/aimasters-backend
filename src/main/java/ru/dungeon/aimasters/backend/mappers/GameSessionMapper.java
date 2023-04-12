package ru.dungeon.aimasters.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.dungeon.aimasters.backend.domain.entities.GameSession;
import ru.dungeon.aimasters.backend.domain.entities.User;
import ru.dungeon.aimasters.backend.dtos.session.GameSessionRequestDto;
import ru.dungeon.aimasters.backend.dtos.session.GameSessionResponseDto;
import ru.dungeon.aimasters.backend.dtos.user.UserRequestDto;
import ru.dungeon.aimasters.backend.dtos.user.UserResponseDto;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Mapper(componentModel = "spring")
public interface GameSessionMapper {

  @Mapping(target = "host", ignore = true)
  @Mapping(target = "status", constant = "CREATED")
  GameSession toGameSessionEntity(GameSessionRequestDto gameSessionRequestDto);

  @Mapping(target = "hostId", source = "host.id")
  GameSessionResponseDto toGameSessionResponseDto(GameSession gameSession);
}
