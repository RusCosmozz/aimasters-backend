package ru.dungeon.aimasters.backend.dtos.chat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Формат ответов от нейросети
 *
 * @author Ermakov KS
 * @since 06.04.2023
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MessageContent {

  /**
   * действие, совершаемое в запросе/ответе
   */
  private ChatAction action;
  /**
   * сгенерированный текст, может быть в разном формате в зависимости от запроса
   */
  private String message;
}
