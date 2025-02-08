package org.cotato.tlinkserver.domain.lectureFile.infra.repository;

import java.util.List;

import org.cotato.tlinkserver.domain.lectureFile.LectureFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LectureFileRepository extends JpaRepository<LectureFile, Long> {

	@Query("SELECT lf.s3Key FROM LectureFile lf WHERE lf.lectureFileBox.id = :lectureFileBoxId")
	List<String> findS3KeysByLectureFileBoxId(@Param("lectureFileBoxId") Long lectureFileBoxId);

}
