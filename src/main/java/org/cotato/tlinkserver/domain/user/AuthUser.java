package org.cotato.tlinkserver.domain.user;

import lombok.Getter;
import org.cotato.tlinkserver.domain.user.constant.Role;

@Getter
public class AuthUser {

    private long id;
    private final SocialProvider socialProvider;
    private final String socialProfileUrl;
    private final Role role;

    private AuthUser(SocialProvider socialProvider, String socialProfileUrl, Role role) {
        this.socialProvider = socialProvider;
        this.socialProfileUrl = socialProfileUrl;
        this.role = role;
    }

    private AuthUser(long id, SocialProvider socialProvider, String socialProfileUrl, Role role) {
        this.id = id;
        this.socialProvider = socialProvider;
        this.socialProfileUrl = socialProfileUrl;
        this.role = role;
    }

    public static AuthUser create(
            SocialProvider socialProvider,
            String socialProfileUrl
    ) {
        return new AuthUser(socialProvider, socialProfileUrl, Role.ONBOARDING);
    }

    public static AuthUser createWithId(
            long id,
            SocialProvider socialProvider,
            String socialProfileUrl
    ) {
        return new AuthUser(id, socialProvider, socialProfileUrl, Role.ONBOARDING);
    }

    public static AuthUser toAuthUser(User user) {
        return new AuthUser(
                user.getId(),
                user.getProvider(),
                user.getProfilePath(),
                user.getRole()
        );
    }
}
