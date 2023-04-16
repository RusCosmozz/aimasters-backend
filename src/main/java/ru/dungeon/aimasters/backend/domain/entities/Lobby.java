package ru.dungeon.aimasters.backend.domain.entities;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.Set;

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
@NamedEntityGraph(
        name = "lobby-with-worlds",
        attributeNodes = {@NamedAttributeNode("worlds")})
public class Lobby extends BaseUUIDEntity {

  @OneToOne
  @JoinColumn(name = "host_id", referencedColumnName = "id")
  private User host;

  @Column(name = "name")
  private String name;

  @Column(name = "status", nullable = false)
  private String status;

  @EqualsAndHashCode.Exclude
  @ToString.Exclude
  @OneToMany(mappedBy = "lobby")
  private Set<World> worlds;
}

