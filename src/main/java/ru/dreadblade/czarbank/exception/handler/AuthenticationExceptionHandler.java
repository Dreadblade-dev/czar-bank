package ru.dreadblade.czarbank.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.dreadblade.czarbank.exception.CzarBankSecurityException;
import ru.dreadblade.czarbank.api.model.response.CzarBankErrorResponseDTO;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class AuthenticationExceptionHandler {
    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<CzarBankErrorResponseDTO> handleBadCredentialsException(BadCredentialsException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(CzarBankErrorResponseDTO.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .message("Invalid username and/or password")
                .path(request.getRequestURI())
                .build());
    }

    @ExceptionHandler(AccountStatusException.class)
    public ResponseEntity<CzarBankErrorResponseDTO> handleAccountStatusException(AccountStatusException exception, HttpServletRequest request) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value()).body(CzarBankErrorResponseDTO.builder()
                .status(HttpStatus.UNAUTHORIZED.value())
                .error(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .message(exception.getMessage())
                .path(request.getRequestURI())
                .build());
    }
}
