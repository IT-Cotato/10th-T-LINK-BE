package org.cotato.tlinkserver.domain.homework.infra.repository;

import org.cotato.tlinkserver.domain.homework.Homework;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {
}
