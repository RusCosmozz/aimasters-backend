package ru.dungeon.aimasters.backend.exceptions.dtos;

import lombok.Data;

/**
 * Структура сообщения об ошибке
 *
 * @author Arkadiy Bezgerts
 * @since 20.03.2023
 */
@Data
public class ErrorResponse {

    /**
     * Сообщение об ошибке
     */
    private String message;
}
