package org.cotato.tlinkserver.api.facade.dto.response;

import org.cotato.tlinkserver.domain.user.User;

public record MyPageInfoDTO(
        String username,
        String phoneNumber,
        String profileUrl,
        String statusMessage
) {
    public static MyPageInfoDTO from(final User user) {
        return new MyPageInfoDTO(
                user.getUsername(),
                user.getPhoneNumber(),
                user.getProfileUrl(),
                user.getStatusMessage()
        );
    }
}
