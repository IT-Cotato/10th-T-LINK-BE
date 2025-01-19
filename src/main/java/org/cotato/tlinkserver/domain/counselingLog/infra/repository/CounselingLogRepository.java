package org.cotato.tlinkserver.domain.counselingLog.infra.repository;

import org.cotato.tlinkserver.domain.counselingLog.CounselingLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CounselingLogRepository extends JpaRepository<CounselingLog, Long> {
}
