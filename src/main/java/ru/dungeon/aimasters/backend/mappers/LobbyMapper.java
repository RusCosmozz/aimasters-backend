package ru.dungeon.aimasters.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.util.CollectionUtils;
import ru.dungeon.aimasters.backend.domain.entities.Lobby;
import ru.dungeon.aimasters.backend.domain.entities.World;
import ru.dungeon.aimasters.backend.dtos.lobby.CompactLobbyResponseDto;
import ru.dungeon.aimasters.backend.dtos.lobby.LobbyRequestDto;
import ru.dungeon.aimasters.backend.dtos.lobby.LobbyResponseDto;
import ru.dungeon.aimasters.backend.dtos.world.LobbyWorldDto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
@Mapper(componentModel = "spring")
public interface LobbyMapper {

    @Mapping(target = "host", ignore = true)
    @Mapping(target = "status", constant = "CREATED")
    Lobby toLobbyEntity(LobbyRequestDto lobbyRequestDto);

    @Mapping(target = "hostId", source = "host.id")
    @Mapping(target = "worlds", qualifiedByName = "toLobbyWorldList")
    LobbyResponseDto toLobbyResponseDto(Lobby lobby);

    CompactLobbyResponseDto toCompactLobbyResponseDto(Lobby lobby);

    LobbyWorldDto toLobbyWorld(World world);

    @Named( "toLobbyWorldList")
    default List<LobbyWorldDto> toLobbyWorldList(Collection<World> worlds) {
        if (CollectionUtils.isEmpty(worlds)) {
            return Collections.emptyList();
        }
        return worlds.stream()
                .map(this::toLobbyWorld)
                .collect(Collectors.toList());
    }
}
