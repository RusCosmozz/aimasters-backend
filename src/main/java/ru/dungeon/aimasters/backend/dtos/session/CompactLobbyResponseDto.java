package ru.dungeon.aimasters.backend.dtos.session;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
public class CompactLobbyResponseDto {
    private UUID id;
    private String name;
}
