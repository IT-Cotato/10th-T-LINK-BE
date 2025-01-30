package org.cotato.tlinkserver.domain.user.infra.repository;

import org.cotato.tlinkserver.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
