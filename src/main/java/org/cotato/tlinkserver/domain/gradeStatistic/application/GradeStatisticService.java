package org.cotato.tlinkserver.domain.gradeStatistic.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.domain.gradeStatistic.ExamBox;
import org.cotato.tlinkserver.domain.gradeStatistic.infra.repository.ExamBoxRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GradeStatisticService {

    private final ExamBoxRepository examBoxRepository;

    public List<ExamBox> getExamBoxes(Long roomId) {
        return examBoxRepository.findExamBoxesByRoomId(roomId);
    }

}
