package ru.dungeon.aimasters.backend.exceptions.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

/**
 * Дто для возвращаемых возникших ошибок
 *
 * @author Goncharov AI
 * @since 21.03.2023
 */
@Data
@AllArgsConstructor
public class ExceptionResponseDto {

    private String message;

    private HttpStatus httpStatus;

}
