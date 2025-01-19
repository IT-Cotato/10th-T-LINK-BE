package org.cotato.tlinkserver.domain.bank.application.dto.response;

import org.cotato.tlinkserver.domain.room.Room;

public record DepositModifyResponse
	(
		String accountNumber,
		Long bankId,
		String bankName,
		int depositAt,
		int depositAmount
	)
{
	public static DepositModifyResponse from(final Room room) {
		return new DepositModifyResponse(
			room.getAccountNumber(),
			room.getBank().getId(),
			room.getBank().getName(),
			room.getDepositAt(),
			room.getDepositAmount()
		);
	}
}
