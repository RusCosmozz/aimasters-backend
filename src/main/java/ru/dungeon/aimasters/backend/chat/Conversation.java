package ru.dungeon.aimasters.backend.chat;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.dungeon.aimasters.backend.dtos.chat.ChatMessageDto;

/**
 * Класс для поддержания контекста разговора с нейросетью
 *
 * @author Ermakov KS
 * @since 06.04.2023
 */
@Getter
@NoArgsConstructor
public class Conversation {

  private List<ChatMessageDto> messages = new ArrayList<>();

  public void addMessage(ChatMessageDto message) {
    messages.add(message);
  }
}
