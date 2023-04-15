package ru.dungeon.aimasters.backend.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;
import ru.dungeon.aimasters.backend.dtos.user.UserDetailsWithId;
import ru.dungeon.aimasters.backend.security.token.JwtTokenService;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@AllArgsConstructor
public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenService jwtTokenService;
    @PostConstruct
    private void init() {
        setAuthenticationManager(authenticationManager);
        setFilterProcessesUrl("/api/users/login");
        setAuthenticationSuccessHandler((request, response, authentication) -> {
            UserDetailsWithId userDetails = (UserDetailsWithId) authentication.getPrincipal();
            String token = jwtTokenService.generateToken(userDetails);
            response.setContentType("application/json");
            response.getWriter().write("{\"token\": \"" + token + "\"}");
        });


        setAuthenticationFailureHandler((request, response, exception) -> {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.getWriter().write("{\"error\": \"" + exception.getMessage() + "\"}");
        });
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) {
        try {
            Map<String, String> requestMap = new ObjectMapper()
                    .readValue(request.getInputStream(), Map.class);

            String email = requestMap.get("email");
            String password = requestMap.get("password");

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(email, password);
            return this.getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
