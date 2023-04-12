package ru.dungeon.aimasters.backend.controllers;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import ru.dungeon.aimasters.backend.domain.entities.User;
import ru.dungeon.aimasters.backend.dtos.user.UserRequestDto;
import ru.dungeon.aimasters.backend.dtos.user.UserResponseDto;
import ru.dungeon.aimasters.backend.mappers.UserMapper;
import ru.dungeon.aimasters.backend.services.UserService;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Slf4j
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

  private final UserService userService;
  private final PasswordEncoder passwordEncoder;

  @PostMapping("/register")
  @ResponseStatus(HttpStatus.CREATED)
  public UserResponseDto register(@RequestBody UserRequestDto userRequestDto) {
    userRequestDto.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
    return userService.register(userRequestDto);
  }


  @GetMapping("/{userId}")
  public UserResponseDto getUserById(@PathVariable UUID userId) {
    return userService.findUserById(userId);
  }

  @PutMapping("/{userId}")
  public UserResponseDto updateUser(@PathVariable UUID userId, @RequestBody UserRequestDto userUpdateDto) {
    return userService.updateUser(userId, userUpdateDto);
  }

  @DeleteMapping("/{userId}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void deleteUser(@PathVariable UUID userId) {
    userService.deleteUser(userId);
  }
}
