package ru.dungeon.aimasters.backend.dtos.character;

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
public class CharacterRequestDto {

  private String name;
  private String race;
  /**
   * если нейросеть придумала расу, отличную от стандартных, то здесь будет описание
   */
  private String raceOverview;
  private String className;
  /**
   * если нейросеть придумала класс, отличный от стандартных, то здесь будет описание
   */
  private String classOverview;
  private String gender;
  /**
   * предыстроия персонажа
   */
  private String backstory;
}
