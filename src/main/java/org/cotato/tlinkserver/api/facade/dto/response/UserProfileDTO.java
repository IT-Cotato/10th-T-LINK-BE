package org.cotato.tlinkserver.api.facade.dto.response;

import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.constant.Role;

public record UserProfileDTO(
        String username,
        String statusMessage,
        String phoneNumber,
        String profileImageUrl,
        Role role
) {
    public static UserProfileDTO from(final User user) {
        return new UserProfileDTO(
                user.getUsername(),
                user.getStatusMessage(),
                user.getPhoneNumber(),
                user.getProfileUrl(),
                user.getRole()
        );
    }
}
