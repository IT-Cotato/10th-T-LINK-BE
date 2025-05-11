package org.cotato.tlinkserver.domain.bank.application.dto.response;

import lombok.Builder;
import org.cotato.tlinkserver.domain.room.Room;

@Builder
public record DepositResponse
        (
                String accountNumber,
                String bankName,
                int depositAt,
                int depositAmount
        ) {
    public static DepositResponse from(final Room room) {
        if (room.getBank() == null) {
            return DepositResponse.builder()
                    .build();
        }
        return new DepositResponse(
                room.getAccountNumber(),
                room.getBank().getName(),
                room.getDepositAt(),
                room.getDepositAmount()
        );
    }
}
