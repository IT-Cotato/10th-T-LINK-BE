package org.cotato.tlinkserver.domain.bank.infra.repository;

import org.cotato.tlinkserver.domain.bank.Bank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankRepository extends JpaRepository<Bank, Long> {
}
