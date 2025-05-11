package org.cotato.tlinkserver.domain.gradeStatistic.infra.repository;

import java.util.List;
import org.cotato.tlinkserver.domain.gradeStatistic.ExamBox;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamBoxRepository extends JpaRepository<ExamBox, Long> {
    List<ExamBox> findExamBoxesByRoomId(Long roomId);
}
