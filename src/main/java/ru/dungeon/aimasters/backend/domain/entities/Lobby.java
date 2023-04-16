package ru.dungeon.aimasters.backend.domain.entities;

import javax.persistence.*;

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
@Table(name = "lobbies")
@EqualsAndHashCode(callSuper = true)
public class Lobby extends BaseUUIDEntity {

  @OneToOne
  @JoinColumn(name = "host_id", referencedColumnName = "id")
  private User host;

  @Column(name = "name")
  private String name;

  @Column(name = "status", nullable = false)
  private String status;
}
