package org.cotato.tlinkserver.api.facade;

import org.cotato.tlinkserver.global.util.S3FileHandler;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class CounselingLogFacade {

	private final S3FileHandler s3FileHandler;

}
