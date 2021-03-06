package ru.dreadblade.czarbank.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.dreadblade.czarbank.api.model.response.CzarBankErrorResponseDTO;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class AuthorizationExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<CzarBankErrorResponseDTO> handleAccessDeniedException(AccessDeniedException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.FORBIDDEN.value()).body(CzarBankErrorResponseDTO.builder()
                .status(HttpStatus.FORBIDDEN.value())
                .error(HttpStatus.FORBIDDEN.getReasonPhrase())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build());
    }
}
