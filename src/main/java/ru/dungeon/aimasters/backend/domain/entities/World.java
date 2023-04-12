package ru.dungeon.aimasters.backend.domain.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Data
@Entity
@Table(name = "worlds")
@EqualsAndHashCode(callSuper = true)
public class World extends BaseUUIDEntity {

  @Column(name = "world_name")
  private String worldName;

  @Column(name = "description")
  private String description;

  @OneToOne
  @JoinColumn(name = "game_session_id", referencedColumnName = "id")
  private GameSession gameSession;

}
