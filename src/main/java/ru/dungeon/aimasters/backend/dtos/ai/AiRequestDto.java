package ru.dungeon.aimasters.backend.dtos.ai;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.dungeon.aimasters.backend.dtos.chat.ChatMessageDto;

/**
 * @author Ermakov KS
 * @since 05.04.2023
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AiRequestDto {

  String model;
  List<ChatMessageDto> messages;
  Double temperature;
//  @JsonProperty("top_p")
//  Double topP;
//  Integer n;
//  Boolean stream;
//  List<String> stop;
//  @JsonProperty("max_tokens")
//  Integer maxTokens;
//  @JsonProperty("presence_penalty")
//  Double presencePenalty;
//  @JsonProperty("frequency_penalty")
//  Double frequencyPenalty;
//  String user;
}

