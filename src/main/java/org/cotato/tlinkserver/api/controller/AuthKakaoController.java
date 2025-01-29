package org.cotato.tlinkserver.api.controller;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.api.dto.response.KakaoUserInfoResponse;
import org.cotato.tlinkserver.domain.auth.kakao.KakaoService;
import org.cotato.tlinkserver.global.message.SuccessMessage;
import org.cotato.tlinkserver.global.util.ApiResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/kakao")
public class AuthKakaoController {

    private final KakaoService kakaoService;

    @GetMapping("/signup")
    public ResponseEntity<?> callback(
            @RequestParam("code") String code
    ) {
        String accessToken = kakaoService.getAccessTokenFromKakao(code);
        KakaoUserInfoResponse userInfo = kakaoService.getUserInfo(accessToken);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS);
    }
}
