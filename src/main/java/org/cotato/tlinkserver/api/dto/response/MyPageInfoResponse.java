package org.cotato.tlinkserver.api.dto.response;

import org.cotato.tlinkserver.api.facade.dto.response.MyPageInfoDTO;

public record MyPageInfoResponse(
        String username,
        String phoneNumber,
        String profileUrl,
        String statusMessage
) {
    public static MyPageInfoResponse from(MyPageInfoDTO myPageInfoDTO) {
        return new MyPageInfoResponse(
                myPageInfoDTO.username(),
                myPageInfoDTO.phoneNumber(),
                myPageInfoDTO.profileUrl(),
                myPageInfoDTO.statusMessage()
        );
    }
}
