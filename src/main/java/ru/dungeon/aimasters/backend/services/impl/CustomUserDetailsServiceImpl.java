package ru.dungeon.aimasters.backend.services.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import ru.dungeon.aimasters.backend.domain.entities.User;
import ru.dungeon.aimasters.backend.domain.dtos.user.UserDetailsWithId;
import ru.dungeon.aimasters.backend.exceptions.exceptions.AuthException;
import ru.dungeon.aimasters.backend.repositories.UserRepository;
import ru.dungeon.aimasters.backend.services.CustomUserDetailsService;

import java.util.ArrayList;

@Slf4j
@Service
@AllArgsConstructor
@Transactional(readOnly = true)
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
    private UserRepository userRepository;

    /**
     * Загружает пользователя из базы по идентификатору (в нашем случае это емейл)
     *
     * @param email емейл пользователя
     * @return UserDetails отображение пользователя
     */
    @Override
    public UserDetails loadUserByUsername(String email) {
        if (!StringUtils.hasLength(email)) {
            throw new AuthException("Email is null");
        }
        log.info("\n Authorization with email: {}", email);
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new AuthException("User not found with email: " + email));
        log.info("\n Found user {} by email: {}", user.getId(), email);
        return new UserDetailsWithId(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                new ArrayList<>()
        );
    }
}
