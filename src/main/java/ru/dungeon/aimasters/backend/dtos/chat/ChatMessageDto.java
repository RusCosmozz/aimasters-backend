package ru.dungeon.aimasters.backend.dtos.chat;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dungeon.aimasters.backend.utils.json.ChatMessageDtoDeserializer;
import ru.dungeon.aimasters.backend.utils.json.ChatMessageDtoSerializer;

/**
 * Дто для передачи и получения сообщений от нейросети
 *
 * @author Ermakov KS
 * @since 05.04.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
//нейросеть не принимает content в виде json, поэтому парсим в строку
@JsonSerialize(using = ChatMessageDtoSerializer.class)
@JsonDeserialize(using = ChatMessageDtoDeserializer.class)
public class ChatMessageDto {

  /**
   * от кого пришло сообщение
   */
  private ChatRole role;
  /**
   * сообщение
   */
  private MessageContent content;

}
