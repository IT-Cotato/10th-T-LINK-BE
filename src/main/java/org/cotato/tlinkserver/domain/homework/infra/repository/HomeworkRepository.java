package org.cotato.tlinkserver.domain.homework.infra.repository;

import java.util.List;

import org.cotato.tlinkserver.domain.homework.Homework;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface HomeworkRepository extends JpaRepository<Homework, Long> {

	@Query("SELECT h FROM Homework h WHERE h.room.id = :roomId")
	List<Homework> findHomeworksByRoomId(@Param("roomId") Long roomId);

}
