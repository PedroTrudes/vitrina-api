package br.com.vitrina_api.modules.invitation.dto;

import java.time.LocalDateTime;

public record InviteResponse(
        String token,
        String storeName,
        LocalDateTime expiresAt
) {
}
