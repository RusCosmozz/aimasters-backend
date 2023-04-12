package ru.dungeon.aimasters.backend.exceptions.exceptions;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
public class EnumNotFoundException extends CommonException {

    public EnumNotFoundException(String text, Class<?> enumClass) {
        super(String.format("Unsupported value %s for enum %s", text, enumClass.getName()));
    }
}
