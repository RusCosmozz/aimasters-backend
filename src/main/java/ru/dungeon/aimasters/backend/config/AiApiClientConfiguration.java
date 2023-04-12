package ru.dungeon.aimasters.backend.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Настройка подключения к нейросети
 *
 * @author Ermakov KS
 * @since 05.04.2023
 */
@Configuration
@RequiredArgsConstructor
public class AiApiClientConfiguration {

  private final AiConfig aiConfig;

  @Bean
  @Qualifier("aiApiClient")
  public WebClient webClient() {
    return WebClient.builder()
                    .baseUrl(aiConfig.getApiBaseUrl())
                    .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + aiConfig.getApiKey())
                    .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                    .build();
  }
}
