package org.cotato.tlinkserver.domain.room.infra.repository;

import java.util.Optional;
import org.cotato.tlinkserver.domain.room.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Optional<Room> findByShareCode(String shareCode);
}
