package ru.dungeon.aimasters.backend.dtos.ai;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dungeon.aimasters.backend.dtos.chat.ChatAnswer;

/**
 * Дто для ответа от нейросети
 *
 * @author Ermakov KS
 * @since 05.04.2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AiResponseDto {

  /**
   * название модели, сгенерировавшей ответ
   */
  String model;

  /**
   * список ответов (в нашем случае содержит только 1 ответ)
   */
  List<ChatAnswer> choices;
}
