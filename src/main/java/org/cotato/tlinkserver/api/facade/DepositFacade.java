package org.cotato.tlinkserver.api.facade;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.domain.bank.Bank;
import org.cotato.tlinkserver.domain.bank.application.BankService;
import org.cotato.tlinkserver.domain.bank.application.dto.request.DepositRequest;
import org.cotato.tlinkserver.domain.bank.application.dto.response.DepositModifyResponse;
import org.cotato.tlinkserver.domain.bank.application.dto.response.DepositResponse;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.application.RoomService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DepositFacade {

    private final RoomService roomService;
    private final BankService bankService;

    @Transactional(readOnly = true)
    public DepositResponse getDeposit(final Long roomId) {
        Room room = roomService.getRoom(roomId);
        return DepositResponse.from(room);
    }

    @Transactional(readOnly = true)
    public DepositModifyResponse getDepositModify(final Long roomId) {
        Room room = roomService.getRoom(roomId);
        return DepositModifyResponse.from(room);
    }

    @Transactional
    public void modifyDeposit(final Long roomId, final DepositRequest depositRequest) {
        Room room = roomService.getRoom(roomId);
        Bank bank = bankService.getBank(depositRequest.bankId());
        room.setAccountNumber(depositRequest.accountNumber());
        room.setDepositAt(depositRequest.depositAt());
        room.setDepositAmount(depositRequest.depositAmount());
        room.setBank(bank);
    }
}
