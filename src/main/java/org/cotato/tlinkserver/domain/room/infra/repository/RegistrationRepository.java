package org.cotato.tlinkserver.domain.room.infra.repository;

import java.util.List;
import org.cotato.tlinkserver.domain.room.Registration;
import org.cotato.tlinkserver.domain.user.constant.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RegistrationRepository extends JpaRepository<Registration, Long> {

    Registration findRegistrationByUserIdAndRoomId(Long userId, Long roomId);

    Registration findRegistrationByRoomIdAndRole(Long roomId, Role role);

    List<Registration> findRegistrationsByUserId(Long userId);

    @Query(
            "SELECT r"
                    + " FROM Registration r"
                    + " JOIN FETCH r.room rr JOIN FETCH rr.lessonDays JOIN FETCH rr.studentPermission JOIN FETCH rr.parentPermission"
                    + " WHERE r.user.id = :userId")
    List<Registration> findFetchRegistrationsByUserId(@Param("userId") long userId);
}
