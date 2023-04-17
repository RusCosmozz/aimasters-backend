package ru.dungeon.aimasters.backend.domain.entities;


import javax.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "characters")
public class PlayableCharacter extends BaseUUIDEntity {

  @ManyToOne
  @JoinColumn(name = "world_id", referencedColumnName = "id")
  private World world;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "race", nullable = false)
  private String race;
  @Column(name = "race_overview", nullable = false)
  private String raceOverview;

  @Column(name = "class", nullable = false)
  private String className;
  @Column(name = "class_overview", nullable = false)
  private String classOverview;

  @Column(name = "gender", nullable = false)
  //todo енам
  private String gender;

  @Column(name = "level", nullable = false)
  private Integer level;

  @Column(name = "backstory")
  private String backstory;

  //todo backpack
  //todo атрибуты
}
