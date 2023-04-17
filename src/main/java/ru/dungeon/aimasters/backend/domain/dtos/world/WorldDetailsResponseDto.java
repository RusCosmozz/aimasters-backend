package ru.dungeon.aimasters.backend.domain.dtos.world;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dungeon.aimasters.backend.domain.dtos.character.CharacterResponseDto;
import ru.dungeon.aimasters.backend.domain.dtos.story.StoryResponseDto;

import java.util.Collection;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class WorldDetailsResponseDto {
    private UUID id;
    private String worldName;
    private String description;
    private Collection<CharacterResponseDto> characters;
    private Collection<StoryResponseDto> stories;
}
