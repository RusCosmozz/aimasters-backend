package ru.dungeon.aimasters.backend.services;

import ru.dungeon.aimasters.backend.domain.dtos.lobby.CompactLobbyResponseDto;
import ru.dungeon.aimasters.backend.domain.dtos.lobby.LobbyRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.lobby.LobbyResponseDto;
import ru.dungeon.aimasters.backend.domain.dtos.story.StoryRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.story.StoryResponseDto;

import java.util.List;
import java.util.UUID;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
public interface StoryService {

  StoryResponseDto createStory(
      StoryRequestDto storyRequestDto,
      UUID worldId);

  List<StoryResponseDto> getStoriesByWorldId(UUID worldId);

}
