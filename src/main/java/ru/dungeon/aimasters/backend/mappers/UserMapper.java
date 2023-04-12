package ru.dungeon.aimasters.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import ru.dungeon.aimasters.backend.domain.entities.User;
import ru.dungeon.aimasters.backend.dtos.user.UserRequestDto;
import ru.dungeon.aimasters.backend.dtos.user.UserResponseDto;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Mapper(componentModel = "spring")
public interface UserMapper {

  @Mapping(target = "roles", constant = "player")
  User toUserEntity(UserRequestDto userRequestDto);

  UserResponseDto toUserResponseDto(User user);

  void updateUser(UserRequestDto updateData, @MappingTarget User user);
}
