package org.cotato.tlinkserver.api.dto.request;

import jakarta.validation.constraints.NotNull;

public record RewriteStatusMessageRequest(
        @NotNull
        String statusMessage
) {
}
