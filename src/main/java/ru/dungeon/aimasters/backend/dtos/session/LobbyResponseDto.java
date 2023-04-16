package ru.dungeon.aimasters.backend.dtos.session;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dungeon.aimasters.backend.domain.entities.World;
import ru.dungeon.aimasters.backend.dtos.world.WorldResponseDto;

import static com.fasterxml.jackson.annotation.JsonInclude.*;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class LobbyResponseDto {

  private UUID id;
  private UUID hostId;
  private String status;
  private String name;
  private List<WorldResponseDto> worlds;
}
