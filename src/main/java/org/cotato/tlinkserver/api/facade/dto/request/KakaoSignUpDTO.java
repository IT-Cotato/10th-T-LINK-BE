package org.cotato.tlinkserver.api.facade.dto.request;

import org.cotato.tlinkserver.api.dto.request.KakaoSignUpRequest;
import org.cotato.tlinkserver.domain.user.constant.Gender;
import org.cotato.tlinkserver.domain.user.constant.Role;

public record KakaoSignUpDTO(
        String code,
        Role role,
        String name,
        String phoneNumber,
        Gender gender,
        String backgroundColor
) {
    public static KakaoSignUpDTO from(final KakaoSignUpRequest kakaoSignUpRequest) {
        return new KakaoSignUpDTO(
                kakaoSignUpRequest.code(),
                kakaoSignUpRequest.role(),
                kakaoSignUpRequest.name(),
                kakaoSignUpRequest.phoneNumber(),
                kakaoSignUpRequest.gender(),
                kakaoSignUpRequest.backgroundColor()
        );
    }
}
