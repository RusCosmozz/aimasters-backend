package ru.dungeon.aimasters.backend.domain.entities;

import javax.persistence.*;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Set;
import java.util.UUID;

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
  @JoinColumn(name = "world_id", referencedColumnName = "id")
  private World world;

  @Column(name = "title")
  private String title;

  @Column(name = "description")
  private String description;

  @Column(name = "status")
  //todo enum
  private String status;

  @ElementCollection(fetch = FetchType.LAZY)
  @CollectionTable(
          name = "character_story",
          joinColumns = @JoinColumn(name = "story_id")
  )
  @Column(name = "character_id")
  private Set<UUID> characters;
}
