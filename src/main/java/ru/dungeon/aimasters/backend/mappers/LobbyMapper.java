package ru.dungeon.aimasters.backend.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.dungeon.aimasters.backend.domain.entities.Lobby;
import ru.dungeon.aimasters.backend.dtos.session.CompactLobbyResponseDto;
import ru.dungeon.aimasters.backend.dtos.session.LobbyRequestDto;
import ru.dungeon.aimasters.backend.dtos.session.LobbyResponseDto;

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
    LobbyResponseDto toLobbyResponseDto(Lobby lobby);

    CompactLobbyResponseDto toCompactLobbyResponseDto(Lobby lobby);
}
