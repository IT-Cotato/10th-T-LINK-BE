package org.cotato.tlinkserver.domain.room.infra.repository;

import java.util.List;
import java.util.Optional;

import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

	Optional<Registration> findRegistrationByUserIdAndRoomId(Long userId, Long roomId);
	Optional<Registration> findRegistrationByRoomIdAndRole(Long roomId, Role role);
	List<Registration> findRegistrationsByUserId(Long userId);
	List<Registration> findRegistrationsByRoomId(Long roomId);


	@Query("SELECT r FROM Registration r WHERE r.room.id = :roomId")
	List<Registration> findAllByRoomId(@Param("roomId") Long roomId);

}
