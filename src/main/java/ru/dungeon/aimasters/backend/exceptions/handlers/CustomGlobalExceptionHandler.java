package ru.dungeon.aimasters.backend.exceptions.handlers;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CONFLICT;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import ru.dungeon.aimasters.backend.chat.Conversation;
import ru.dungeon.aimasters.backend.domain.dtos.chat.ChatMessageDto;
import ru.dungeon.aimasters.backend.domain.dtos.chat.ChatRole;
import ru.dungeon.aimasters.backend.exceptions.dtos.ExceptionResponseDto;
import ru.dungeon.aimasters.backend.exceptions.exceptions.*;

/**
 * Обработчик ошибок со всего приложения
 *
 * @author Goncharov AI
 * @since 21.03.2023
 */
@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(CONFLICT)
    public ExceptionResponseDto handleConflictException(Exception e) {
        log.error(e.getMessage());
        return new ExceptionResponseDto(e.getMessage(), CONFLICT);
    }

    @ExceptionHandler({MethodArgumentTypeMismatchException.class, EnumNotFoundException.class})
    @ResponseStatus(BAD_REQUEST)
    public ExceptionResponseDto handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        log.error(ex.getMessage());
        return new ExceptionResponseDto(ex.getMessage(), BAD_REQUEST);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponseDto handleNotFoundException(Exception e) {
        log.error(e.getMessage());
        return new ExceptionResponseDto(e.getMessage(), NOT_FOUND);
    }

    @ExceptionHandler({InsufficientAuthenticationException.class, AccessDeniedException.class, InternalAuthenticationServiceException.class})
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ExceptionResponseDto handleSecurityException(Exception ex) {
        log.error(ex.getMessage());
        return new ExceptionResponseDto(ex.getMessage(), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler({AuthException.class})
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ExceptionResponseDto handleAuthException(Exception ex) {
        log.error(ex.getMessage());
        return new ExceptionResponseDto(ex.getMessage(), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({WebClientResponseException.class, Exception.class})
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionResponseDto handleWebClientResponseException(
            WebClientResponseException ex) {

        log.error(ex.getMessage());
        return new ExceptionResponseDto(ex.getResponseBodyAsString(), INTERNAL_SERVER_ERROR);
    }

    private void deleteIncorrectMessagesFromConversation(Conversation conversation) {
        if (!conversation.getMessages().isEmpty()) {
            List<ChatMessageDto> messages = conversation.getMessages();
            // если исключение произошло после ответа нейросети, то удалим и запрос и ответ
            // если нет, то удалим только запрос
            if (messages.get(messages.size() - 1).getRole().equals(ChatRole.ASSISTANT)) {
                messages.remove(messages.size() - 1);
                messages.remove(messages.size() - 2);
            } else {
                messages.remove(messages.size() - 1);
            }
        }
    }

    @ExceptionHandler(JsonParsingException.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionResponseDto handleJsonParsingException(
            JsonParsingException ex) {


        log.error(ex.getMessage());
        return new ExceptionResponseDto(ex.getMessage(), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponseDto handleDataIntegrityViolationException(DataIntegrityViolationException ex) {
        log.error(ex.getMessage());
        String message = NestedExceptionUtils.getMostSpecificCause(ex).getMessage();
        return new ExceptionResponseDto(message, HttpStatus.CONFLICT);
    }
}
