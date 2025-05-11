package org.cotato.tlinkserver.domain.homework.infra.repository;

import org.cotato.tlinkserver.domain.homework.HomeworkFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkFileRepository extends JpaRepository<HomeworkFile, Long> {
}
