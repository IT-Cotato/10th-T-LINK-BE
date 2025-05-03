package org.cotato.tlinkserver.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.cotato.tlinkserver.domain.user.constant.Role;

@Getter
public class AuthUser {

    @Setter
    private Long id;
    private String socialId;
    private final SocialProvider socialProvider;
    private final String socialProfileUrl;
    private final Role role;

    @Builder
    private AuthUser(Long id, String socialId, SocialProvider socialProvider, String socialProfileUrl, Role role) {
        this.id = id;
        this.socialId = socialId;
        this.socialProvider = socialProvider;
        this.socialProfileUrl = socialProfileUrl;
        this.role = role;
    }

    public static AuthUser create(
            SocialProvider socialProvider,
            String socialProfileUrl
    ) {
        return AuthUser.builder()
                .socialProvider(socialProvider)
                .socialProfileUrl(socialProfileUrl)
                .role(Role.ONBOARDING)
                .build();
    }

    public static AuthUser createWithId(
            String socialId,
            SocialProvider socialProvider,
            String socialProfileUrl
    ) {
        return AuthUser.builder()
                .socialId(socialId)
                .socialProvider(socialProvider)
                .socialProfileUrl(socialProfileUrl)
                .role(Role.ONBOARDING)
                .build();
    }

    public static AuthUser toAuthUser(User user) {
        return AuthUser.builder()
                .id(user.getId())
                .socialId(user.getSocialId())
                .socialProvider(user.getProvider())
                .socialProfileUrl(user.getProfileUrl())
                .role(user.getRole())
                .build();
    }
}
