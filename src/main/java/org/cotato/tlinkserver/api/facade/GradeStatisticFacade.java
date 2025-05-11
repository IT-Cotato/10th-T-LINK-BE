package org.cotato.tlinkserver.api.facade;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.annotation.Facade;
import org.cotato.tlinkserver.domain.gradeStatistic.Exam;
import org.cotato.tlinkserver.domain.gradeStatistic.ExamBox;
import org.cotato.tlinkserver.domain.gradeStatistic.application.GradeStatisticService;
import org.cotato.tlinkserver.domain.gradeStatistic.application.dto.request.SaveExamBoxRequest;
import org.cotato.tlinkserver.domain.gradeStatistic.application.dto.request.SaveExamRequest;
import org.cotato.tlinkserver.domain.gradeStatistic.application.dto.response.ExamBoxResponse;
import org.cotato.tlinkserver.domain.gradeStatistic.application.dto.response.ExamBoxesResponse;
import org.cotato.tlinkserver.domain.gradeStatistic.application.dto.response.ExamResponse;
import org.cotato.tlinkserver.domain.gradeStatistic.application.dto.response.ExamsResponse;
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

    @Transactional
    public void saveExam(final SaveExamRequest saveExamRequest, final Long examBoxId) {
        ExamBox examBox = gradeStatisticService.getExamBox(examBoxId);
        Exam exam = saveExamRequest.toEntity();
        examBox.addExam(exam);
    }

    @Transactional(readOnly = true)
    public ExamsResponse getExams(Long examBoxId) {
        List<Exam> exams = gradeStatisticService.getExamBox(examBoxId).getExams();
        return ExamsResponse.from(exams.stream().map(ExamResponse::from).toList());
    }

    @Transactional
    public void removeExam(final Long examBoxId, final Long examId) {
        ExamBox examBox = gradeStatisticService.getExamBox(examBoxId);
        Exam exam = gradeStatisticService.getExam(examId);
        examBox.getExams().remove(exam);
    }
}
