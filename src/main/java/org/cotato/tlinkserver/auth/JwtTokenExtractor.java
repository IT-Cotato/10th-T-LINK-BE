package org.cotato.tlinkserver.auth;

import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JwtTokenExtractor {

    private final JwtProperties jwtProperties;
    private final KeyGenerator keyGenerator;

    public String getSubject(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(keyGenerator.getKeyFromString(jwtProperties.secretKey()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public Role getRole(String token) {
        String role = Jwts.parserBuilder()
                .setSigningKey(keyGenerator.getKeyFromString(jwtProperties.secretKey()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("role", String.class);

        return Role.valueOf(role);
    }
}
