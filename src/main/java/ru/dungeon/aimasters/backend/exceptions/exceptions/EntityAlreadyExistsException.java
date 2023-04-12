package ru.dungeon.aimasters.backend.exceptions.exceptions;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
public class EntityAlreadyExistsException extends CommonException {

    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
