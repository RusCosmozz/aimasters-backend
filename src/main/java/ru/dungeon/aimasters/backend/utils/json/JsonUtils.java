package ru.dungeon.aimasters.backend.utils.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import ru.dungeon.aimasters.backend.exceptions.exceptions.JsonParsingException;

/**
 * @author Ermakov KS
 * @since 06.04.2023
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JsonUtils {

  private static final ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

  public static String toJson(Object object) {
    try {
      return objectMapper.writeValueAsString(object);
    } catch (JsonProcessingException e) {
      throw new JsonParsingException(String.format("Cannot parse object %s to json", object));
    }
  }

  public static <T> T fromJson(String json, Class<T> clazz) {
    try {
      return objectMapper.readValue(json, clazz);
    } catch (JsonProcessingException e) {
      throw new JsonParsingException(String.format("Cannot parse json %s to class %s", json, clazz));
    }
  }
}
