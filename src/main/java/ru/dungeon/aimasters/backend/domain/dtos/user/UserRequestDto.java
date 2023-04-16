package ru.dungeon.aimasters.backend.domain.dtos.user;

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
public class UserRequestDto {

  private String username;

  private String email;

  private String password;
}
