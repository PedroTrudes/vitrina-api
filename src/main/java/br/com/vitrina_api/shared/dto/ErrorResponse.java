package br.com.vitrina_api.shared.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
   String message,
   String errorCode,
   int status,
   String path,
   LocalDateTime timestamp
) {
}
