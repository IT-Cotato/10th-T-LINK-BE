package org.cotato.tlinkserver.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SocialInfoResult(
        Long id,
        @JsonProperty("kakao_account")
        KakaoAccount kakaoAccount
) {
}
