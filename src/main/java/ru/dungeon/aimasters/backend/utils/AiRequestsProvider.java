package ru.dungeon.aimasters.backend.utils;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.dungeon.aimasters.backend.dtos.ai.AiRequestDto;
import ru.dungeon.aimasters.backend.dtos.chat.ChatAction;
import ru.dungeon.aimasters.backend.dtos.chat.ChatMessageDto;

/**
 * @author Ermakov KS
 * @since 05.04.2023
 */
@Slf4j
@Component
@AllArgsConstructor
public class AiRequestsProvider {


  public AiRequestDto createGameSessionRequest() {
    ChatMessageDto message = ChatMessageBuilder.buildGameSessionMessage();
    log.info("{}", message);
    return AiRequestDto.builder()
                       .model("gpt-3.5-turbo")
                       .temperature(0.4)
                       .messages(List.of(message))
                       .build();
  }

  public AiRequestDto createWorldRequest(List<ChatMessageDto> conversation) {
    log.info("{}", conversation.get(conversation.size() - 1));
    return AiRequestDto.builder()
                       .model("gpt-3.5-turbo")
                       .temperature(0.7)
                       .messages(conversation)
                       .build();
  }

  public AiRequestDto createNewCharacterRequest(List<ChatMessageDto> conversation) {
    log.info("{}", conversation.get(conversation.size() - 1));
    return AiRequestDto.builder()
                       .model("gpt-3.5-turbo")
                       .temperature(0.7)
                       .messages(conversation)
                       .build();
  }
}
