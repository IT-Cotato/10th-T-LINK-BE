package org.cotato.tlinkserver.domain.bank.application;

import java.util.List;

import org.cotato.tlinkserver.domain.bank.Bank;
import org.cotato.tlinkserver.domain.bank.infra.repository.BankRepository;
import org.springframework.stereotype.Service;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class BankService {

	private final BankRepository bankRepository;

	public List<Bank> getBanks() {
		return bankRepository.findAll();
	}

}
