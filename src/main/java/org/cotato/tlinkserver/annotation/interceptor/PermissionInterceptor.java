package org.cotato.tlinkserver.annotation.interceptor;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cotato.tlinkserver.annotation.Permission;
import org.cotato.tlinkserver.auth.JwtTokenExtractor;
import org.cotato.tlinkserver.auth.TokenParser;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.cotato.tlinkserver.global.exception.TLinkException;
import org.cotato.tlinkserver.global.exception.UnauthorizedException;
import org.cotato.tlinkserver.global.message.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.resource.ResourceHttpRequestHandler;

@Slf4j
@Component
@RequiredArgsConstructor
public class PermissionInterceptor implements HandlerInterceptor {

    private final JwtTokenExtractor jwtTokenExtractor;
    private final TokenParser tokenParser;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (handler instanceof ResourceHttpRequestHandler)
            return true;

        HandlerMethod method = (HandlerMethod) handler;
        Permission permission = method.getMethodAnnotation(Permission.class);
        if (permission == null)
            return true;

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        Role role;

        try {
            role = jwtTokenExtractor.getRole(tokenParser.getToken(token));
        } catch (NullPointerException e) {
            throw UnauthorizedException.empty();
        } catch (ExpiredJwtException e) {
            throw UnauthorizedException.expired();
        } catch (JwtException | IllegalArgumentException e) {
            throw UnauthorizedException.wrong();
        }

        Set<Role> annotations = Set.of(permission.role());

        if (annotations.contains(Role.ONBOARDING)) {
            if (role == Role.ONBOARDING) {
                log.info("Successfully authenticated as Onboarding");
                return true; // Check @Permission
            }
        }

        if (annotations.contains(Role.STUDENT)) {
            if (role == Role.STUDENT) {
                log.info("Successfully authenticated as Student");
                return true; // Check @Permission
            }
        }

        if (annotations.contains(Role.PARENT)) {
            if (role == Role.PARENT) {
                log.info("Successfully authenticated as Parent");
                return true; // Check @Permission
            }
        }

        if (annotations.contains(Role.TEACHER)) {
            if (role == Role.TEACHER) {
                log.info("Successfully authenticated as Teacher");
                return true; // Check @Permission
            }
        }

        throw new TLinkException(ErrorMessage.FORBIDDEN);
    }
}
