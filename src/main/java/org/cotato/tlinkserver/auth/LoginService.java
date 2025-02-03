package org.cotato.tlinkserver.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.cotato.tlinkserver.auth.command.LoginCommand;
import org.cotato.tlinkserver.auth.dto.LoginResult;
import org.cotato.tlinkserver.auth.dto.OauthTokenResult;
import org.cotato.tlinkserver.auth.dto.SocialInfoResult;
import org.cotato.tlinkserver.domain.token.RefreshToken;
import org.cotato.tlinkserver.domain.token.infra.RefreshTokenRepository;
import org.cotato.tlinkserver.domain.user.AuthUser;
import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.infra.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class LoginService {

    private final OauthClientApi oauthClientApi;
    private final UserRepository userRepository;
    private final JwtTokenGenerator jwtTokenGenerator;
    private final RefreshTokenRepository refreshTokenRepository;

    @Transactional
    public LoginResult login(LoginCommand command) {

        SocialInfoResult socialUserInfo = getSocialInfo(command);
        AuthUser authUser = loadOrCreateUser(command, socialUserInfo);
        Token token = createToken(authUser);
        updateRefreshToken(token.refreshToken(), authUser.getId());
        User user = userRepository.findById(authUser.getId()).get();

        return LoginResult.of(token, user.isOnboarding());
    }

    private AuthUser loadOrCreateUser(LoginCommand command, SocialInfoResult socialUserInfo) {
        AuthUser retrievedAuthUser = userRepository.findByIdAndProvider(
                socialUserInfo.id(),
                command.provider()
        )
                .map(AuthUser::toAuthUser)
                .orElse(null);

        if (retrievedAuthUser != null) {
            return retrievedAuthUser;
        }

        AuthUser createAuthUser = AuthUser.createWithId(
                socialUserInfo.id(),
                command.provider(),
                socialUserInfo.kakaoAccount().profile().profileImageUrl()
        );

        userRepository.save(
                User.create(createAuthUser)
        );

        return createAuthUser;
    }

    private void updateRefreshToken(String refreshToken, long id) {
        refreshTokenRepository.save(
                RefreshToken.of(refreshToken, id)
        );
    }

    private Token createToken(AuthUser authUser) {
        return new Token(
                jwtTokenGenerator.createAccessToken(String.valueOf(authUser.getId()), authUser.getRole()),
                jwtTokenGenerator.createRefreshToken(String.valueOf(authUser.getId()), authUser.getRole())
        );
    }

    private SocialInfoResult getSocialInfo(LoginCommand command) {
        OauthTokenResult tokenResult = oauthClientApi.getAccessToken(command.redirectUrl(), command.code());
        SocialInfoResult socialUserInfo = oauthClientApi.getSocialUserInfo(tokenResult.accessToken());
        return socialUserInfo;
    }
}
