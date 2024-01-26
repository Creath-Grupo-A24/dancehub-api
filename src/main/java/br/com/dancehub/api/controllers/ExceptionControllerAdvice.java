package br.com.dancehub.api.controllers;

import br.com.dancehub.api.exceptions.NotFoundEntityException;
import br.com.dancehub.api.validation.FieldError;
import br.com.dancehub.api.validation.exceptions.ValidationException;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
@Slf4j
public class ExceptionControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CustomErrorResponse> handleAllExceptions(Exception ex, HttpServletRequest request) {
        final CustomErrorResponse errorResponse = new CustomErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                null
        );

        log.error(ex.getMessage(), ex.getCause());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<CustomErrorResponse> handleNotificationException(ValidationException ex, HttpServletRequest request) {
        final CustomErrorResponse errorResponse = new CustomErrorResponse(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST.value(),
                ex.getErrors().stream().map(ErrorResponse::from).toList()
        );

        log.error(ex.getMessage(), ex.getCause());

        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(NotFoundEntityException.class)
    public ResponseEntity<CustomErrorResponse> handleNotFoundException(NotFoundEntityException ex, HttpServletRequest request) {
        final CustomErrorResponse errorResponse = new CustomErrorResponse(
                ex.getMessage(),
                HttpStatus.NOT_FOUND.value(),
                null
        );

        log.error(ex.getMessage(), ex.getCause());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @JsonInclude(JsonInclude.Include.NON_NULL)
    public record CustomErrorResponse(
            @JsonProperty("message")
            String message,
            @JsonProperty("status")
            int status,
            @JsonProperty("errors")
            List<ErrorResponse> errors
    ) {
    }

    public record ErrorResponse(
            @JsonProperty("code")
            Integer code,
            @JsonProperty("message")
            String message
    ) {
        public static ErrorResponse from(FieldError error) {
            return new ErrorResponse(error.message().getCode(), error.message().getText(error.field()));
        }
    }

}
