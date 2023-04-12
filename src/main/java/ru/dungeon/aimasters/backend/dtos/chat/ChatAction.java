package ru.dungeon.aimasters.backend.dtos.chat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import ru.dungeon.aimasters.backend.exceptions.exceptions.EnumNotFoundException;

/**
 * Енам для описания действий, совершаемых в запросе/ответе
 *
 * @author Ermakov KS
 * @since 05.04.2023
 */
public enum ChatAction {
  GAME_SESSION_REQ("gameSessionInitRequest"),
  GAME_SESSION_RES("gameSessionInitResponse"),
  WORLD_CREATION_REQ("worldCreationRequest"),
  WORLD_CREATION_RES("worldCreationResponse"),
  CHARACTER_CREATION_REQ("characterCreationRequest"),
  CHARACTER_CREATION_RES("characterCreationResponse");

  private final String text;

  ChatAction(String text) {
    this.text = text;
  }

  @JsonCreator
  public static ChatAction fromValue(String text) {
    for (ChatAction r : ChatAction.values()) {
      if (String.valueOf(r.text).equalsIgnoreCase(text)) {
        return r;
      }
    }
    throw new EnumNotFoundException(text, ChatAction.class);
  }

  @JsonValue
  public String getText() {
    return text;
  }

}
