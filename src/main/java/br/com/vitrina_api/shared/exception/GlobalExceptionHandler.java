package br.com.vitrina_api.shared.exception;

import br.com.vitrina_api.shared.dto.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponse> handleBusiness(BusinessException ex, HttpServletRequest request){
        HttpStatus status = resolveStatus(ex.getErrorCode());
        return buildResponse(
                ex.getMessage(),
                ex.getErrorCode().name(),
                status,
                request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGeneric(Exception ex, HttpServletRequest request){
        return buildResponse("Erro interno do servidor", ErrorCode.INTERNAL_ERROR.name(), HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    private ResponseEntity<ErrorResponse> buildResponse(String message, String errorCode ,HttpStatus status, HttpServletRequest request) {
        ErrorResponse error = new ErrorResponse(message, errorCode, status.value(), request.getRequestURI(), LocalDateTime.now());
        return ResponseEntity.status(status).body(error);
    }

    private HttpStatus resolveStatus(ErrorCode errorCode){
        return switch (errorCode){
            case INVITE_NOT_FOUND -> HttpStatus.NOT_FOUND;
            case INVITE_EXPIRED, INVITE_INVALID -> HttpStatus.BAD_REQUEST;

            case INVALID_CREDENTIALS, UNAUTHORIZED -> HttpStatus.UNAUTHORIZED;

            case VALIDATION_ERROR -> HttpStatus.BAD_REQUEST;

            default -> HttpStatus.INTERNAL_SERVER_ERROR;
        };
    }
}
