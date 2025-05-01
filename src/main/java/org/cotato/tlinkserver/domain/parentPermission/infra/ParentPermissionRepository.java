package org.cotato.tlinkserver.domain.parentPermission.infra;

import org.cotato.tlinkserver.domain.parentPermission.ParentPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentPermissionRepository extends JpaRepository<ParentPermission, Long> {
}
