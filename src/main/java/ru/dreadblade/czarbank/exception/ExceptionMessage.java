package ru.dreadblade.czarbank.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ExceptionMessage {
    BANK_ACCOUNT_TYPE_NOT_FOUND("Bank account type doesn't exist", HttpStatus.NOT_FOUND),
    CURRENCY_NOT_FOUND("Currency doesn't exist", HttpStatus.NOT_FOUND),
    LATEST_EXCHANGE_RATES_NOT_FOUND("Error while loading the latest currency rates", HttpStatus.INTERNAL_SERVER_ERROR),
    EXCHANGE_RATES_AT_DATE_NOT_FOUND("No exchange rates found for the date", HttpStatus.NOT_FOUND),
    BANK_ACCOUNT_NOT_FOUND("Bank account doesn't exist", HttpStatus.NOT_FOUND),
    PERMISSION_NOT_FOUND("Permission doesn't exist", HttpStatus.NOT_FOUND),
    ROLE_NOT_FOUND("Role doesn't exist", HttpStatus.NOT_FOUND),
    USER_NOT_FOUND("User doesn't exist", HttpStatus.NOT_FOUND),

    BANK_ACCOUNT_TYPE_NAME_ALREADY_EXISTS("Bank account type with same name already exists", HttpStatus.BAD_REQUEST),
    CURRENCY_CODE_ALREADY_EXISTS("Currency with same code already exists", HttpStatus.BAD_REQUEST),
    CURRENCY_SYMBOL_ALREADY_EXISTS("Currency with same symbol already exists", HttpStatus.BAD_REQUEST),
    ROLE_NAME_ALREADY_EXISTS("Role with same name already exists", HttpStatus.BAD_REQUEST),
    USERNAME_ALREADY_EXISTS("User with same username already exists", HttpStatus.BAD_REQUEST),
    USER_EMAIL_ALREADY_EXISTS("Role with same email already exists", HttpStatus.BAD_REQUEST),

    SOURCE_BANK_ACCOUNT_DOESNT_EXIST("Source bank account doesn't exist", HttpStatus.BAD_REQUEST),
    DESTINATION_BANK_ACCOUNT_DOESNT_EXIST("Destination bank account doesn't exist", HttpStatus.BAD_REQUEST),
    BANK_ACCOUNT_TYPE_IN_USE("Bank account type in use", HttpStatus.BAD_REQUEST),
    NOT_ENOUGH_BALANCE("Not enough balance", HttpStatus.BAD_REQUEST),
    UNSUPPORTED_CURRENCY("Currency is not supported", HttpStatus.BAD_REQUEST),
    EMAIL_ADDRESS_ALREADY_VERIFIED("Email address already verified", HttpStatus.BAD_REQUEST),
    EMAIL_VERIFICATION_TOKEN_EXPIRED("We have sent a new email with a link to verify your account to " +
            "the email address you provided when you created your account", HttpStatus.BAD_REQUEST),
    EMAIL_VERIFICATION_REQUIRED("Please check your email and follow the link to verify your email address", HttpStatus.UNAUTHORIZED),
    TWO_FACTOR_AUTHENTICATION_ALREADY_SETUP("Two-factor authentication is already set up", HttpStatus.BAD_REQUEST),
    SETUP_TWO_FACTOR_AUTHENTICATION("You need to set up two-factor authentication", HttpStatus.BAD_REQUEST),

    REFRESH_TOKEN_EXPIRED("Refresh token expired", HttpStatus.BAD_REQUEST),
    INVALID_REFRESH_TOKEN("Invalid refresh token", HttpStatus.BAD_REQUEST),
    INVALID_ACCESS_TOKEN("Invalid access token", HttpStatus.BAD_REQUEST),
    INVALID_EMAIL_VERIFICATION_TOKEN("Invalid email verification token", HttpStatus.BAD_REQUEST),
    INVALID_TWO_FACTOR_AUTHENTICATION_CODE("Invalid two-factor authentication code", HttpStatus.BAD_REQUEST),
    INVALID_TWO_FACTOR_AUTHENTICATION_CODE_AUTH_FAILED("Invalid two-factor authentication code", HttpStatus.UNAUTHORIZED),
    INVALID_RECOVERY_CODE("Invalid recovery code", HttpStatus.BAD_REQUEST),
    INVALID_RECOVERY_CODE_AUTH_FAILED("Invalid recovery code", HttpStatus.UNAUTHORIZED),
    RECOVERY_CODE_ALREADY_USED("Recovery code already used", HttpStatus.BAD_REQUEST),
    RECOVERY_CODE_ALREADY_USED_AUTH_FAILED("Recovery code already used", HttpStatus.UNAUTHORIZED),

    TOTP_QR_CODE_GENERATION_FAILED("QR code generation failed. If the problem persists, please, contact support!", HttpStatus.INTERNAL_SERVER_ERROR);

    private final String message;
    private final HttpStatus status;

    ExceptionMessage(String message, HttpStatus status) {
        this.message = message;
        this.status = status;
    }
}
