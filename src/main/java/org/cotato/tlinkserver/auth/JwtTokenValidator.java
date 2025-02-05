package org.cotato.tlinkserver.auth;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.global.exception.UnauthorizedException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenValidator {

    private final KeyGenerator keyGenerator;
    private final JwtProperties jwtProperties;

    public void validate(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(keyGenerator.getKeyFromString(jwtProperties.secretKey()))
                    .build()
                    .parseClaimsJws(token);
        } catch (SecurityException | MalformedJwtException | IllegalArgumentException | UnsupportedJwtException e) {
            throw UnauthorizedException.wrong();
        } catch (ExpiredJwtException e) {
            throw UnauthorizedException.expired();
        }
    }
}
