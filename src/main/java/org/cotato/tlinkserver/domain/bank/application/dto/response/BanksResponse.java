package org.cotato.tlinkserver.domain.bank.application.dto.response;

import java.util.List;

import lombok.Builder;

@Builder
public record BanksResponse
	(
		List<BankResponse> banks
	)
{
	public static BanksResponse from(List<BankResponse> banks) {
		return BanksResponse.builder()
			.banks(banks)
			.build();
	}
}
