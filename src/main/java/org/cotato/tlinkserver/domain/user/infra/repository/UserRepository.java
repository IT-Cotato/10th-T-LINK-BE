package org.cotato.tlinkserver.domain.user.infra.repository;

import java.util.Optional;
import org.cotato.tlinkserver.domain.user.SocialProvider;
import org.cotato.tlinkserver.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByIdAndProvider(long id, SocialProvider provider);
}
