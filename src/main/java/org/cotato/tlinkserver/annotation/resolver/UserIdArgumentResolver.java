package org.cotato.tlinkserver.annotation.resolver;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cotato.tlinkserver.annotation.UserId;
import org.cotato.tlinkserver.auth.JwtTokenExtractor;
import org.cotato.tlinkserver.auth.TokenParser;
import org.cotato.tlinkserver.global.exception.UnauthorizedException;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserIdArgumentResolver implements HandlerMethodArgumentResolver {

    private final JwtTokenExtractor jwtTokenExtractor;
    private final TokenParser tokenParser;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.info("Enter supportsParameter Function");
        return parameter.getParameterType().equals(Long.class)
                && parameter.hasParameterAnnotation(UserId.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {

        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (Objects.isNull(token)) {
            return null;
        }

        String userId;
        try {
            userId = jwtTokenExtractor.getSubject(tokenParser.getToken(token));
        } catch (ExpiredJwtException e) {
            throw UnauthorizedException.expired();
        } catch (JwtException | IllegalArgumentException e) {
            throw UnauthorizedException.wrong();
        }

        return Long.valueOf(userId);
    }
}
