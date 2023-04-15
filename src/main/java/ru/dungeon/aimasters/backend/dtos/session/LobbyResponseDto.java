package ru.dungeon.aimasters.backend.dtos.session;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LobbyResponseDto {

  private UUID id;
  private UUID hostId;
  private String status;
  private String name;
}