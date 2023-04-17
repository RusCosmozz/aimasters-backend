package ru.dungeon.aimasters.backend.services.impl;

import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.dungeon.aimasters.backend.domain.entities.User;
import ru.dungeon.aimasters.backend.domain.dtos.user.UserRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.user.UserResponseDto;
import ru.dungeon.aimasters.backend.exceptions.exceptions.EntityAlreadyExistsException;
import ru.dungeon.aimasters.backend.exceptions.exceptions.EntityNotFoundException;
import ru.dungeon.aimasters.backend.mappers.UserMapper;
import ru.dungeon.aimasters.backend.repositories.UserRepository;
import ru.dungeon.aimasters.backend.services.CommonService;
import ru.dungeon.aimasters.backend.services.UserService;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private CommonService commonService;
  private UserMapper userMapper;


  @Override
  @Transactional
  public UserResponseDto register(UserRequestDto userRequestDto) {
    if (userRepository.existsByEmail(userRequestDto.getEmail())) {
      throw new EntityAlreadyExistsException("Email already exists.");
    }
    User userEntity = userMapper.toUserEntity(userRequestDto);
    User savedUser = userRepository.save(userEntity);
    return userMapper.toUserResponseDto(savedUser);
  }

  @Override
  public UserResponseDto findUserById(UUID userId) {
    User user = commonService.getUsersByIdIfExists(userId);
    return userMapper.toUserResponseDto(user);
  }

  @Override
  @Transactional
  public UserResponseDto updateUser(UUID userId, UserRequestDto userUpdateDto) {
    User user = commonService.getUsersByIdIfExists(userId);
    userMapper.updateUser(userUpdateDto, user);
    User updatedUser = userRepository.save(user);
    return userMapper.toUserResponseDto(updatedUser);
  }

  @Override
  @Transactional
  public void deleteUser(UUID userId) {
    User user = commonService.getUsersByIdIfExists(userId);
    userRepository.delete(user);
  }

}
