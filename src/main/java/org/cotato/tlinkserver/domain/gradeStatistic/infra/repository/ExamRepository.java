package org.cotato.tlinkserver.domain.gradeStatistic.infra.repository;

import org.cotato.tlinkserver.domain.gradeStatistic.Exam;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<Exam, Long> {
}
