package org.cotato.tlinkserver.domain.lectureFile.infra.repository;

import org.cotato.tlinkserver.domain.lectureFile.LectureFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureFileRepository extends JpaRepository<LectureFile, Long> {
}
