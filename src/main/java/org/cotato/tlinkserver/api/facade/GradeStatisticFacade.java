package org.cotato.tlinkserver.api.facade;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.annotation.Facade;
import org.cotato.tlinkserver.domain.gradeStatistic.ExamBox;
import org.cotato.tlinkserver.domain.gradeStatistic.application.GradeStatisticService;
import org.cotato.tlinkserver.domain.gradeStatistic.application.dto.request.SaveExamBoxRequest;
import org.cotato.tlinkserver.domain.gradeStatistic.application.dto.response.ExamBoxResponse;
import org.cotato.tlinkserver.domain.gradeStatistic.application.dto.response.ExamBoxesResponse;
import org.cotato.tlinkserver.domain.room.Room;
import org.cotato.tlinkserver.domain.room.application.RoomService;
import org.springframework.transaction.annotation.Transactional;

@Facade
@RequiredArgsConstructor
public class GradeStatisticFacade {

    private final RoomService roomService;
    private final GradeStatisticService gradeStatisticService;

    @Transactional
    public void saveExamBox(final SaveExamBoxRequest saveExamBoxRequest, final Long roomId) {
        Room room = roomService.getRoom(roomId);
        ExamBox examBox = saveExamBoxRequest.toEntity();
        room.addExamBox(examBox);
    }

    @Transactional(readOnly = true)
    public ExamBoxesResponse getExamBoxes(Long roomId) {
        List<ExamBox> examBoxes = gradeStatisticService.getExamBoxes(roomId);
        return ExamBoxesResponse.from(examBoxes.stream().map(ExamBoxResponse::from).toList());
    }

    @Transactional
    public void removeExamBox(final Long roomId, final Long examBoxId) {
        Room room = roomService.getRoom(roomId);
        ExamBox examBox = gradeStatisticService.getExamBox(examBoxId);
        room.getExamBoxes().remove(examBox);
    }
}
