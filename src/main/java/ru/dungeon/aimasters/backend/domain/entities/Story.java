package ru.dungeon.aimasters.backend.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Data
@Entity
@Table(name = "stories")
@EqualsAndHashCode(callSuper = true)
public class Story extends BaseUUIDEntity {

  @ManyToOne
  @JoinColumn(name = "game_session_id", referencedColumnName = "id")
  private GameSession gameSession;

  @ManyToOne
  @JoinColumn(name = "world_id", referencedColumnName = "id")
  private World world;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "status")
  //todo enum
  private String status;
}
