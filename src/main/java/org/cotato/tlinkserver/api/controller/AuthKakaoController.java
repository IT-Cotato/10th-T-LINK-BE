package org.cotato.tlinkserver.api.controller;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.api.dto.request.KakaoSignUpRequest;
import org.cotato.tlinkserver.api.facade.AuthKakaoFacade;
import org.cotato.tlinkserver.api.facade.dto.request.KakaoSignUpDTO;
import org.cotato.tlinkserver.global.message.SuccessMessage;
import org.cotato.tlinkserver.global.util.ApiResponseUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth/kakao")
public class AuthKakaoController {

    private final AuthKakaoFacade authKakaoFacade;

    @GetMapping("/signup")
    public ResponseEntity<?> callback(
            @RequestBody KakaoSignUpRequest kakaoSignUpRequest
    ) {
        authKakaoFacade.kakaoSignUp(KakaoSignUpDTO.from(kakaoSignUpRequest));
        return ApiResponseUtil.success(SuccessMessage.SUCCESS);
    }
}
