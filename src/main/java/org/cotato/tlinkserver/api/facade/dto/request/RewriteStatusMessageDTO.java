package org.cotato.tlinkserver.api.facade.dto.request;

import org.cotato.tlinkserver.api.dto.request.RewriteStatusMessageRequest;

public record RewriteStatusMessageDTO(
        String statusMessage
) {
    public static RewriteStatusMessageDTO from(RewriteStatusMessageRequest request) {
        return new RewriteStatusMessageDTO(
                request.statusMessage()
        );
    }
}
