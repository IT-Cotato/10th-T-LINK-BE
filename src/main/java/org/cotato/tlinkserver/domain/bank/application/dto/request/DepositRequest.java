package org.cotato.tlinkserver.domain.bank.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DepositRequest
        (
                @NotNull
                Long bankId,
                @NotBlank
                String accountNumber,
                int depositAt,
                int depositAmount
        ) {

}
