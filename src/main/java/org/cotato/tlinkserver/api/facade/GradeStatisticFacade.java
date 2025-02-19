package org.cotato.tlinkserver.api.facade;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.annotation.Facade;
import org.cotato.tlinkserver.domain.gradeStatistic.ExamBox;
import org.cotato.tlinkserver.domain.gradeStatistic.application.dto.request.SaveExamBoxRequest;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.application.RoomService;
import org.springframework.transaction.annotation.Transactional;

@Facade
@RequiredArgsConstructor
public class GradeStatisticFacade {

    private final RoomService roomService;

    @Transactional
    public void saveExamBox(final SaveExamBoxRequest saveExamBoxRequest, final Long roomId) {
        Room room = roomService.getRoom(roomId);
        ExamBox examBox = saveExamBoxRequest.toEntity();
        room.addExamBox(examBox);
    }

}
