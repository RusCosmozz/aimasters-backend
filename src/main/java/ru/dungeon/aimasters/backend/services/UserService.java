package ru.dungeon.aimasters.backend.services;

import java.util.UUID;

import ru.dungeon.aimasters.backend.domain.dtos.user.UserRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.user.UserResponseDto;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
public interface UserService {

  UserResponseDto register(UserRequestDto userRequestDto);

  UserResponseDto findUserById(UUID id);

  UserResponseDto updateUser(UUID id, UserRequestDto userUpdateDto);

  void deleteUser(UUID id);
}