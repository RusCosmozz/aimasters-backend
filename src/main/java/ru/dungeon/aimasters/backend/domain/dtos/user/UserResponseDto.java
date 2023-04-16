package ru.dungeon.aimasters.backend.domain.dtos.user;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

  private UUID id;
  private String username;
  private String email;
}
