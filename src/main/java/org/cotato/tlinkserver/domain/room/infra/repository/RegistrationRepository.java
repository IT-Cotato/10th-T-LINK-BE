package org.cotato.tlinkserver.domain.room.infra.repository;

import java.util.List;

import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import jakarta.persistence.Tuple;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

	@Query("SELECT r.room, r.name FROM Registration r WHERE r.user.id = :userId")
	List<Tuple> findRoomsByUserId(@Param("userId") Long userId);

	@Query("SELECT r.user FROM Registration r WHERE r.room.id = :roomId AND r.user.id != :userId")
	User findOpponent(@Param("roomId") Long roomId, @Param("userId") Long userId);

}
