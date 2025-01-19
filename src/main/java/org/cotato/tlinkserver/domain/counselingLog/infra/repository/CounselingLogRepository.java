package org.cotato.tlinkserver.domain.counselingLog.infra.repository;

import java.util.List;

import org.cotato.tlinkserver.domain.counselingLog.CounselingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CounselingLogRepository extends JpaRepository<CounselingLog, Long> {

	@Query("SELECT cl FROM CounselingLog cl WHERE cl.room.id = :roomId")
	List<CounselingLog> findCounselingLogsByRoomId(@Param("roomId") Long roomId);

}
