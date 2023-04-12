package ru.dungeon.aimasters.backend.domain.entities;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * todo javadoc когда будет ясны модели
 *
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Data
@Entity
@Table(name = "game_sessions")
@EqualsAndHashCode(callSuper = true)
public class GameSession extends BaseUUIDEntity {

  @OneToOne
  @JoinColumn(name = "host_id", referencedColumnName = "id")
  private User host;

  @Column(name = "summary")
  private String summary;

  @Column(name = "name")
  private String name;

  @Column(name = "status", nullable = false)
  private String status;
}
