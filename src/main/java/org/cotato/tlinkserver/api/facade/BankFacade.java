package org.cotato.tlinkserver.api.facade;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.annotation.Permission;
import org.cotato.tlinkserver.domain.bank.application.BankService;
import org.cotato.tlinkserver.domain.bank.application.dto.response.BankResponse;
import org.cotato.tlinkserver.domain.bank.application.dto.response.BanksResponse;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.cotato.tlinkserver.global.util.S3FileHandler;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class BankFacade {

    private final BankService bankService;
    private final S3FileHandler s3FileHandler;
    private final String DIRECTORY_NAME = "bank-logo/";
    private final String FILE_EXTENSION = ".png";

    @Permission(role = {Role.TEACHER})
    @Transactional(readOnly = true)
    public BanksResponse getBanks() {
        List<BankResponse> banks = bankService.getBanks().stream().map(bank -> {
            String bankUrl = s3FileHandler.getFileUrl(DIRECTORY_NAME + bank.getName() + FILE_EXTENSION).toString();
            return BankResponse.from(bank, bankUrl);
        }).toList();

        return BanksResponse.from(banks);
    }

}
