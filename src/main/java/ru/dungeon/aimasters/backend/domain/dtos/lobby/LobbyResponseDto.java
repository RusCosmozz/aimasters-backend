package ru.dungeon.aimasters.backend.domain.dtos.lobby;

import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  private List<LobbyWorldDto> worlds;
}
