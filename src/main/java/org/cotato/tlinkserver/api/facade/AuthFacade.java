package org.cotato.tlinkserver.api.facade;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.annotation.Facade;
import org.cotato.tlinkserver.auth.LoginService;
import org.cotato.tlinkserver.auth.LogoutService;
import org.cotato.tlinkserver.auth.OnboardService;
import org.cotato.tlinkserver.auth.ReissueService;
import org.cotato.tlinkserver.auth.Token;
import org.cotato.tlinkserver.auth.command.LoginCommand;
import org.cotato.tlinkserver.auth.command.OnboardCommand;
import org.cotato.tlinkserver.auth.dto.LoginResult;
import org.cotato.tlinkserver.domain.user.User;
import org.cotato.tlinkserver.domain.user.application.UserService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Facade
@RequiredArgsConstructor
public class AuthFacade {

    private final LoginService loginService;
    private final LogoutService logoutService;
    private final ReissueService reissueService;
    private final OnboardService onboardService;
    private final UserService userService;

    @Transactional
    public LoginResult login(LoginCommand loginCommand) {
        return loginService.login(loginCommand);
    }

    @Transactional
    public Token reissue(String refreshToken) {
        return reissueService.reissue(refreshToken);
    }

    @Transactional
    public void logout(Long userId) {
        logoutService.logout(userId);
    }

    @Transactional
    public void addUserInfo(long userId, OnboardCommand command) {
        onboardService.addUserInfo(userId, command);
    }

    @Transactional(readOnly = true)
    public Token regenerateToken(long userId) {
        User user = getValidUser(userId);
        return reissueService.regenerateToken(user.getId(), user.getRole());
    }

    private User getValidUser(long userId) {
        return userService.getValidUser(userId);
    }
}
