package org.cotato.tlinkserver.domain.bank.application.dto.request;

public record DepositRequest
	(
		String accountNumber,
		int depositAt,
		int depositAmount
	)
{

}
