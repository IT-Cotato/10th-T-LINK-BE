package org.cotato.tlinkserver.domain.parentPermission.application;

import lombok.RequiredArgsConstructor;
import org.cotato.tlinkserver.domain.parentPermission.infra.ParentPermissionRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ParentPermissionService {

    private final ParentPermissionRepository parentPermissionRepository;
}
