package ru.dungeon.aimasters.backend.security.token;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.dungeon.aimasters.backend.config.TokenConfig;
import ru.dungeon.aimasters.backend.dtos.user.UserDetailsWithId;
import ru.dungeon.aimasters.backend.exceptions.exceptions.AuthException;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

@Component
@RequiredArgsConstructor
public class JwtTokenService {

    private final TokenConfig tokenConfig;

    private final UserDetailsService userDetailsService;

    private final String USER_ID_CLAIM = "userId";

    public String generateToken(UserDetailsWithId userDetails) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + tokenConfig.getExpirationTime() * 1000);

        Algorithm algorithm = Algorithm.HMAC256(tokenConfig.getSecretKey());
        return JWT.create()
                // username в userDetails это email в User
                .withSubject(userDetails.getUsername())
                .withClaim(USER_ID_CLAIM, userDetails.getId().toString())
                .withIssuedAt(now)
                .withExpiresAt(expiryDate)
                .sign(algorithm);
    }

    public String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        return resolveToken(bearerToken);
    }

    public String resolveToken(String bearerToken) {
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public UUID getIdFromJwt(String bearerToken) {
        String token = resolveToken(bearerToken);
        String userId = getIdFromToken(token);
        return UUID.fromString(userId);
    }

    public boolean validateToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(tokenConfig.getSecretKey());
            JWTVerifier verifier = JWT.require(algorithm).build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            throw new AuthException(String.format("Token verify exception: %s", e.getMessage()));
        }
    }

    public Authentication getAuthentication(String token) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(getEmail(token));
        return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
    }

    private String getEmail(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getSubject();
    }

    private String getIdFromToken(String token) {
        DecodedJWT decodedJWT = JWT.decode(token);
        return decodedJWT.getClaim(USER_ID_CLAIM).asString();
    }
}
