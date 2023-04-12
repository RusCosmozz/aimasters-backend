package ru.dungeon.aimasters.backend.dtos.chat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.util.Locale;
import ru.dungeon.aimasters.backend.exceptions.exceptions.EnumNotFoundException;

/**
 * Енам для описания отправителя сообщения
 *
 * @author Ermakov KS
 * @since 05.04.2023
 */
public enum ChatRole {
  // сообщение от пользователя
  USER("user"),
  //todo изначально планировалось что нейросеть будет отвечать с ролью Dungeon Master, но пока это не пригодилось
  DM("DM"),
  // сообщение от нейросети
  ASSISTANT("assistant"),
  //системное сообщение настраивает нейросеть и вводит ее в контекст (обычно это первое сообщение)
  SYSTEM("system");

  private final String text;

  ChatRole(String text) {
    this.text = text;
  }

  @JsonCreator
  public static ChatRole fromValue(String text) {
    for (ChatRole r : ChatRole.values()) {
      if (String.valueOf(r.text).equalsIgnoreCase(text)) {
        return r;
      }
    }
    throw new EnumNotFoundException(text, ChatRole.class);
  }

  @JsonValue
  public String getText() {
    return text.toLowerCase(Locale.ROOT);
  }
}
