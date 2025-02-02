package org.cotato.tlinkserver.api.facade;

import java.util.List;

import org.cotato.tlinkserver.domain.bank.application.BankService;
import org.cotato.tlinkserver.domain.bank.application.dto.response.BankResponse;
import org.cotato.tlinkserver.domain.bank.application.dto.response.BanksResponse;
import org.cotato.tlinkserver.global.util.S3FileHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BankFacade {

	private final BankService bankService;
	private final S3FileHandler s3FileHandler;
	private final String FOLDER_NAME = "bank-logo/";
	private final String FILE_EXTENSION = ".png";

	@Transactional(readOnly = true)
	public BanksResponse getBanks() {
		List<BankResponse> banks = bankService.getBanks().stream().map(bank -> {
			String bankUrl = s3FileHandler.getFileUrl(FOLDER_NAME + bank.getName() + FILE_EXTENSION).toString();
			return BankResponse.from(bank, bankUrl);
		}).toList();

		return BanksResponse.from(banks);
	}

}
