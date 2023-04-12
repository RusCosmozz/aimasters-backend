package ru.dungeon.aimasters.backend.dtos.chat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * Дто содержащее информацию о кокретном ответе
 *
 * @author Ermakov KS
 * @since 05.04.2023
 */
@Data
public class ChatAnswer {

  /**
   * сообщение, содержащееся в ответе
   */
  ChatMessageDto message;

  /**
   * причина остановки генерации
   */
  //todo пока не пригодилось, может выпилить?
  @JsonProperty("finish_reason")
  String finishReason;

  public MessageContent getMessageContent() {
    return message.getContent();
  }
}
