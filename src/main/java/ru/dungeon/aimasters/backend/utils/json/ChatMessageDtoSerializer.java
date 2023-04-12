package ru.dungeon.aimasters.backend.utils.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import ru.dungeon.aimasters.backend.dtos.chat.ChatMessageDto;

/**
 * Кастомный сериализатор для ChatMessageDto
 *
 * @author Ermakov KS
 * @since 07.04.2023
 */
public class ChatMessageDtoSerializer extends JsonSerializer<ChatMessageDto> {

  private final ObjectMapper objectMapper = new ObjectMapper();

  @Override
  public void serialize(
      ChatMessageDto chatMessageDto,
      JsonGenerator jsonGenerator,
      SerializerProvider serializerProvider) throws IOException {

    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField("role", chatMessageDto.getRole().getText());
    String contentJsonString = objectMapper.writeValueAsString(chatMessageDto.getContent());
    jsonGenerator.writeStringField("content", contentJsonString);
    jsonGenerator.writeEndObject();
  }
}
