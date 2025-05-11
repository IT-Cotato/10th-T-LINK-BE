package org.cotato.tlinkserver.domain.studentPermission.application;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.domain.studentPermission.infra.StudentPermissionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentPermissionService {

    private final StudentPermissionRepository studentPermissionRepository;
}
