package org.cotato.tlinkserver.api.dto.response;

import org.cotato.tlinkserver.api.facade.dto.response.UserProfileDTO;
import org.cotato.tlinkserver.domain.user.constant.Role;

public record UserProfileResponse(
        String username,
        String statusMessage,
        String phoneNumber,
        String profileImageUrl,
        Role role
) {
    public static UserProfileResponse from(UserProfileDTO userProfileDTO) {
        return new UserProfileResponse(
                userProfileDTO.username(),
                userProfileDTO.statusMessage(),
                userProfileDTO.phoneNumber(),
                userProfileDTO.profileImageUrl(),
                userProfileDTO.role()
        );
    }
}
