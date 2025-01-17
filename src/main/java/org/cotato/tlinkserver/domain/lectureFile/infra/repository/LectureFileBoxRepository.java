package org.cotato.tlinkserver.domain.lectureFile.infra.repository;

import java.util.List;

import org.cotato.tlinkserver.domain.lectureFile.LectureFileBox;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LectureFileBoxRepository extends JpaRepository<LectureFileBox, Long> {

	@Query("SELECT lfb FROM LectureFileBox lfb WHERE lfb.room.id = :roomId")
	List<LectureFileBox> findLectureFileBoxesByRoomId(@Param("roomId") Long roomId);

}
