package ru.dungeon.aimasters.backend.services.impl;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import ru.dungeon.aimasters.backend.chat.Conversation;
import ru.dungeon.aimasters.backend.dtos.chat.ChatAction;
import ru.dungeon.aimasters.backend.utils.AiRequestsProvider;
import ru.dungeon.aimasters.backend.dtos.ai.AiRequestDto;
import ru.dungeon.aimasters.backend.dtos.ai.AiResponseDto;
import ru.dungeon.aimasters.backend.dtos.chat.ChatMessageDto;
import ru.dungeon.aimasters.backend.services.AiService;
import ru.dungeon.aimasters.backend.utils.ChatMessageBuilder;

/**
 * Сервис для работы с нейросетью
 *
 * @author Ermakov KS
 * @since 05.04.2023
 */
@Service
@AllArgsConstructor
public class AiServiceImpl implements AiService {

  @Qualifier("aiApiClient")
  private final WebClient webClient;
  private final AiRequestsProvider aiRequestsProvider;

  private static final String AI_CHAT_COMPLETIONS_URI = "/v1/chat/completions";
  private static final String CONVERSATION_SESSION_KEY = "conversation";

  @Override
  public AiResponseDto startGameSession(HttpSession httpSession) {
    Conversation conversation = getConversation(httpSession);
    AiRequestDto gameSessionRequest = aiRequestsProvider.createGameSessionRequest();
    conversation.addMessage(gameSessionRequest.getMessages().get(0));
    AiResponseDto aiResponseDto = sendAiRequest(gameSessionRequest);
    conversation.getMessages().add(aiResponseDto.getChoices().get(0).getMessage());
    return aiResponseDto;
  }

  @Override
  public AiResponseDto generateWorld(HttpSession httpSession) {
    Conversation conversation = getConversation(httpSession);
    conversation.addMessage(ChatMessageBuilder.buildWorldCreationMessage());
    AiRequestDto worldRequest = aiRequestsProvider.createWorldRequest(conversation.getMessages());
    AiResponseDto aiResponseDto = sendAiRequest(worldRequest);
    conversation.getMessages().add(aiResponseDto.getChoices().get(0).getMessage());
    return aiResponseDto;
  }

  @Override
  public AiResponseDto createCharacter(HttpSession httpSession) {
    Conversation conversation = getConversation(httpSession);
    conversation.addMessage(ChatMessageBuilder.buildCharacterCreationMessage());
    AiRequestDto characterRequest = aiRequestsProvider.createNewCharacterRequest(conversation.getMessages());
    AiResponseDto aiResponseDto = sendAiRequest(characterRequest);
    conversation.getMessages().add(aiResponseDto.getChoices().get(0).getMessage());
    return aiResponseDto;
  }


  private AiResponseDto sendAiRequest(AiRequestDto worldRequest) {
    return webClient.post()
                    .uri(AI_CHAT_COMPLETIONS_URI)
                    .body(BodyInserters.fromValue(worldRequest))
                    .retrieve()
                    .bodyToMono(AiResponseDto.class)
                    .block();
  }

  private Conversation getConversation(HttpSession session) {
    Conversation conversation = (Conversation) session.getAttribute(CONVERSATION_SESSION_KEY);
    if (conversation == null) {
      conversation = new Conversation();
      session.setAttribute(CONVERSATION_SESSION_KEY, conversation);
    }
    return conversation;
  }
}
