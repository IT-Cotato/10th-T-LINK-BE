package org.cotato.tlinkserver.auth.command;

import org.cotato.tlinkserver.domain.user.constant.Gender;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.cotato.tlinkserver.api.dto.request.OnboardRequest;
import org.cotato.tlinkserver.global.exception.TLinkException;
import org.cotato.tlinkserver.global.message.ErrorMessage;

public record OnboardCommand(
        Role role,
        String username,
        String phoneNumber,
        Gender gender,
        String backgroundColor
) {
    public static OnboardCommand toCommand(OnboardRequest request) {
//         온보딩에서 온보딩 역할 할당은 제한됨
        if (request.role() == Role.ONBOARDING) {
            throw new TLinkException(ErrorMessage.BAD_REQUEST);
        }

        return new OnboardCommand(
                request.role(),
                request.username(),
                request.phoneNumber(),
                request.gender(),
                request.backgroundColor()
        );
    }
}
