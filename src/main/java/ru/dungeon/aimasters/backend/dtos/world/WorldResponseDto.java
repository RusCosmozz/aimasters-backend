package ru.dungeon.aimasters.backend.dtos.world;

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
public class WorldResponseDto {

  private UUID id;
  private String worldName;
  private String description;
  private String gameSessionId;
}
