package ru.dungeon.aimasters.backend.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author Ermakov KS
 * @since 06.04.2023
 */
public class TemplateUtils {

  public static String readTemplate(String templateName) {
    try {
      URL resource = TemplateUtils.class.getClassLoader().getResource("promts/" + templateName);
      if (resource == null) {
        throw new FileNotFoundException("Template file not found: " + templateName);
      }

      Path path = Paths.get(resource.toURI());
      return String.join("\n", Files.readAllLines(path));
    } catch (IOException | URISyntaxException e) {
      throw new RuntimeException("Error reading template file: " + templateName, e);
    }
  }

  public static String formatTemplate(String template, Object... args) {
    return String.format(template, args);
  }
}
