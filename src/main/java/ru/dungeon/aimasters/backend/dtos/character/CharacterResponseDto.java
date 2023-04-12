package ru.dungeon.aimasters.backend.dtos.character;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Ermakov KS
 * @since 06.04.2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CharacterResponseDto {

  private UUID id;
  private UUID userId;
  /**
   * идентификатор мира, в котором создан персонаж
   */
  private UUID worldId;
  private String name;
  private String race;
  private String raceOverview;
  private String className;
  private String classOverview;
  private String gender;
  private Integer level;
  private String backstory;
}
