package ru.dungeon.aimasters.backend.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@Data
@Configuration
@ConfigurationProperties(prefix = "token")
public class TokenConfig {

    private String secretKey;
    private Long expirationTime;
}
