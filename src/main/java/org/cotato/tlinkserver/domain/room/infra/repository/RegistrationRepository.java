package org.cotato.tlinkserver.domain.room.infra.repository;

import java.util.List;

import org.cotato.tlinkserver.domain.room.Registration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

	@Query("SELECT r FROM Registration r WHERE r.user.id = :userId")
	List<Registration> findAllByUserId(@Param("userId") Long userId);

}
