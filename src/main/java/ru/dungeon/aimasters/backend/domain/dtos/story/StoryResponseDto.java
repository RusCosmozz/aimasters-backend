package ru.dungeon.aimasters.backend.domain.dtos.story;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoryResponseDto {

    private UUID id;
    private UUID worldId;
    private String title;
    private String description;
    private Set<UUID> characters;
    private String status;
}
