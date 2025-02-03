package org.cotato.tlinkserver.auth;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenGenerator {

    private static final String JWT_CLAIM_ROLE = "role";
    private static final String JWT_IS_ACCESS_TOKEN = "isAccessToken";

    private final JwtProperties jwtProperties;
    private final KeyGenerator keyGenerator;

    public String createAccessToken(String payload, Role role) {

        Date now = new Date();
        Map<String, Object> claims = new HashMap<>();

        claims.put(JWT_CLAIM_ROLE, role);
        claims.put(JWT_IS_ACCESS_TOKEN, true);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setSubject(payload)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.accessTokenValidTime()))
                .signWith(keyGenerator.getKeyFromString(jwtProperties.secretKey()), SignatureAlgorithm.HS256)
                .compact();
    }

    public String createRefreshToken(String payload, Role role) {

        Date now = new Date();
        Map<String, Object> claims = new HashMap<>();

        claims.put(JWT_CLAIM_ROLE, role);
        claims.put(JWT_IS_ACCESS_TOKEN, true);

        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setClaims(claims)
                .setSubject(payload)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime() + jwtProperties.refreshTokenValidTime()))
                .signWith(keyGenerator.getKeyFromString(jwtProperties.secretKey()), SignatureAlgorithm.HS256)
                .compact();
    }
}
