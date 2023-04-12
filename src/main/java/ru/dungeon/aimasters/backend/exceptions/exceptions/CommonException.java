package ru.dungeon.aimasters.backend.exceptions.exceptions;

/**
 * @author Ermakov KS
 * @since 04.04.2023
 */
public abstract class CommonException extends RuntimeException {

  protected CommonException(String message) {
    this(message, null);
  }

  protected CommonException(String message, Throwable cause) {
    super(message, cause);
  }

}
