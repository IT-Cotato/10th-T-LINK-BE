package org.cotato.tlinkserver.domain.bank.application.dto.response;

import lombok.Builder;
import org.cotato.tlinkserver.domain.bank.Bank;

@Builder
public record BankResponse
        (
                Long bankId,
                String bankName,
                String bankUrl
        ) {
    public static BankResponse from(Bank bank, String bankUrl) {
        return BankResponse.builder()
                .bankId(bank.getId())
                .bankName(bank.getName())
                .bankUrl(bankUrl)
                .build();
    }
}
