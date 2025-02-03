package org.cotato.tlinkserver.api.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cotato.tlinkserver.annotation.Permission;
import org.cotato.tlinkserver.annotation.UserId;
import org.cotato.tlinkserver.api.dto.request.LoginRequest;
import org.cotato.tlinkserver.api.dto.request.OnboardRequest;
import org.cotato.tlinkserver.api.facade.AuthFacade;
import org.cotato.tlinkserver.auth.Token;
import org.cotato.tlinkserver.auth.command.LoginCommand;
import org.cotato.tlinkserver.auth.command.OnboardCommand;
import org.cotato.tlinkserver.auth.dto.LoginResult;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.cotato.tlinkserver.global.common.BaseResponse;
import org.cotato.tlinkserver.global.message.SuccessMessage;
import org.cotato.tlinkserver.global.util.ApiResponseUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/auth/kakao")
@RequiredArgsConstructor
public class AuthController {

    private final AuthFacade authFacade;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<?>> login(@RequestBody LoginRequest request) {
        LoginResult result = authFacade.login(
                new LoginCommand(request.provider(), request.redirectUrl(), request.code())
        );
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, result);
    }

    @Permission(role = {Role.ONBOARDING, Role.STUDENT, Role.PARENT, Role.TEACHER})
    @PostMapping("/reissue")
    public ResponseEntity<BaseResponse<?>> reissue(@RequestHeader(HttpHeaders.AUTHORIZATION) String refreshToken) {
        Token token = authFacade.reissue(refreshToken);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, token);
    }

    @Permission(role = {Role.ONBOARDING, Role.STUDENT, Role.PARENT, Role.TEACHER})
    @PostMapping("/logout")
    public ResponseEntity<BaseResponse<?>> logout(
            @UserId Long userId
    ) {
        authFacade.logout(userId);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS);
    }

    @Permission(role = {Role.ONBOARDING})
    @PostMapping("/onboard")
    public ResponseEntity<BaseResponse<?>> onboard(
            @UserId Long userId,
            @RequestBody OnboardRequest request

    ) {
        authFacade.addUserInfo(userId, OnboardCommand.toCommand(request));
        Token token = authFacade.regenerateToken(userId);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, token);
    }
}
