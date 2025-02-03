package org.cotato.tlinkserver.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record KakaoProfile(
        @JsonProperty("profile_image_url")
        String profileImageUrl
) {
}
