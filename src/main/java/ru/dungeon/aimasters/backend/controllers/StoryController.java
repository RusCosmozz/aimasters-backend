package ru.dungeon.aimasters.backend.controllers;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.dungeon.aimasters.backend.domain.dtos.story.StoryRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.story.StoryResponseDto;
import ru.dungeon.aimasters.backend.domain.dtos.world.WorldRequestDto;
import ru.dungeon.aimasters.backend.domain.dtos.world.WorldResponseDto;
import ru.dungeon.aimasters.backend.services.StoryService;
import ru.dungeon.aimasters.backend.services.WorldService;

import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/api/worlds/{worldId}/stories")
@AllArgsConstructor
@Api(tags = "Stories")
public class StoryController {
    private final StoryService storyService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ApiOperation("Создание истории")
    public StoryResponseDto createWorld(
            @RequestBody StoryRequestDto storyRequestDto,
            @PathVariable UUID worldId) {

        return storyService.createStory(storyRequestDto, worldId);
    }
}
