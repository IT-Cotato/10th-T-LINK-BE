package org.cotato.tlinkserver.domain.gradeStatistic.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.domain.gradeStatistic.Exam;
import org.cotato.tlinkserver.domain.gradeStatistic.ExamBox;
import org.cotato.tlinkserver.domain.gradeStatistic.infra.repository.ExamBoxRepository;
import org.cotato.tlinkserver.domain.gradeStatistic.infra.repository.ExamRepository;
import org.cotato.tlinkserver.global.exception.NotFoundException;
import org.cotato.tlinkserver.global.message.ErrorMessage;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeStatisticService {

    private final ExamBoxRepository examBoxRepository;
    private final ExamRepository examRepository;

    public ExamBox getExamBox(Long examBoxId) {
        return examBoxRepository.findById(examBoxId).orElseThrow(()-> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

    public List<ExamBox> getExamBoxes(Long roomId) {
        return examBoxRepository.findExamBoxesByRoomId(roomId);
    }

    public Exam getExam(Long examId) {
        return examRepository.findById(examId).orElseThrow(() -> new NotFoundException(ErrorMessage.NOT_FOUND));
    }

}
