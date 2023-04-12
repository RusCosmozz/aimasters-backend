package ru.dungeon.aimasters.backend.utils;

import ru.dungeon.aimasters.backend.dtos.chat.ChatAction;
import ru.dungeon.aimasters.backend.dtos.chat.ChatMessageDto;
import ru.dungeon.aimasters.backend.dtos.chat.ChatRole;
import ru.dungeon.aimasters.backend.dtos.chat.MessageContent;

/**
 * @author Ermakov KS
 * @since 06.04.2023
 */
public class ChatMessageBuilder {

  public static ChatMessageDto buildWorldCreationMessage() {
    String createWorldPrompt = TemplateUtils.readTemplate("createWorldPrompt.txt");
    return buildMessage(ChatAction.WORLD_CREATION_REQ, ChatRole.USER, createWorldPrompt);
  }

  public static ChatMessageDto buildGameSessionMessage() {
    String gameInitPrompt = TemplateUtils.readTemplate("gameInitPrompt.txt");
    return buildMessage(ChatAction.GAME_SESSION_REQ, ChatRole.SYSTEM, gameInitPrompt);
  }

  public static ChatMessageDto buildCharacterCreationMessage() {
    String createWorldPrompt = TemplateUtils.readTemplate("createCharacterPrompt.txt");
    return buildMessage(ChatAction.CHARACTER_CREATION_REQ, ChatRole.USER, createWorldPrompt);
  }

  private static ChatMessageDto buildMessage(ChatAction action, ChatRole role, String messageText) {
    MessageContent content = MessageContent.builder()
                                           .action(action)
                                           .message(messageText)
                                           .build();
    return ChatMessageDto.builder()
                         .role(role)
                         .content(content)
                         .build();
  }
}
