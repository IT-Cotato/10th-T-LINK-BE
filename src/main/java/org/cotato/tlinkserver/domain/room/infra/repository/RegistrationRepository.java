package org.cotato.tlinkserver.domain.room.infra.repository;

import java.util.List;

import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

	Registration findRegistrationByRoomIdAndRole(Long roomId, Role role);
	List<Registration> findRegistrationsByUserId(Long userId);
	List<Registration> findRegistrationsByRoomId(Long roomId);

}
