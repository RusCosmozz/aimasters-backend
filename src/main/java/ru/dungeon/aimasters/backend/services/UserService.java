package ru.dungeon.aimasters.backend.services;

import java.util.UUID;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.dungeon.aimasters.backend.domain.entities.User;
import ru.dungeon.aimasters.backend.dtos.user.UserRequestDto;
import ru.dungeon.aimasters.backend.dtos.user.UserResponseDto;

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