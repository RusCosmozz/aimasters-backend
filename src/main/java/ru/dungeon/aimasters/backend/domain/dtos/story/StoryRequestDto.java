package ru.dungeon.aimasters.backend.domain.dtos.story;

import liquibase.pro.packaged.S;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoryRequestDto {

    private String title;
    private String description;
    private Set<UUID> characters;
}
