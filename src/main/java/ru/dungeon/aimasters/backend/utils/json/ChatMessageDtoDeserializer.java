package ru.dungeon.aimasters.backend.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import ru.dungeon.aimasters.backend.dtos.chat.ChatMessageDto;
import ru.dungeon.aimasters.backend.dtos.chat.ChatRole;
import ru.dungeon.aimasters.backend.dtos.chat.MessageContent;

/**
 * @author Ermakov KS
 * @since 07.04.2023
 */
public class ChatMessageDtoDeserializer extends JsonDeserializer<ChatMessageDto> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public ChatMessageDto deserialize(
      JsonParser jsonParser,
      DeserializationContext deserializationContext) throws IOException {

    ObjectCodec objectCodec = jsonParser.getCodec();
    JsonNode node = objectCodec.readTree(jsonParser);

    ChatMessageDto chatMessageDto = new ChatMessageDto();
    chatMessageDto.setRole(ChatRole.fromValue(node.get("role").asText()));
    MessageContent content = objectMapper.readValue(node.get("content").asText(), MessageContent.class);
    chatMessageDto.setContent(content);

    return chatMessageDto;
  }
}

