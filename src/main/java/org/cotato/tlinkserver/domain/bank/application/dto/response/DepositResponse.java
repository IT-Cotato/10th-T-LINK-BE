package org.cotato.tlinkserver.domain.bank.application.dto.response;

import org.cotato.tlinkserver.domain.room.Room;

public record DepositResponse
	(
		String accountNumber,
		String bankName,
		int depositAt,
		int depositAmount
	)
{
	public static DepositResponse from(final Room room) {
		return new DepositResponse(
			room.getAccountNumber(),
			room.getBank().getName(),
			room.getDepositAt(),
			room.getDepositAmount()
		);
	}
}
