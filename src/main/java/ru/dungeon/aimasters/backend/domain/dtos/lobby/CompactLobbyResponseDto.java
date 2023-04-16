package ru.dungeon.aimasters.backend.domain.dtos.lobby;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CompactLobbyResponseDto {
    private UUID id;
    private String name;
}
