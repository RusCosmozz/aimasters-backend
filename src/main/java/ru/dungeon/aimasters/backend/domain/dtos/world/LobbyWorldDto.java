package ru.dungeon.aimasters.backend.domain.dtos.world;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LobbyWorldDto {
    private UUID id;
    private String worldName;
    private String description;
}
