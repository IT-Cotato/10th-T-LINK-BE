package org.cotato.tlinkserver.domain.studentPermission.infra;

import org.cotato.tlinkserver.domain.studentPermission.StudentPermission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentPermissionRepository extends JpaRepository<StudentPermission, Long> {
}
