package ru.otus.AleksandrYurkov.telegramBot.globalExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.stream.Collectors;

@ControllerAdvice
public class globalExceptionHandlerController {
    @ExceptionHandler(value = ValidationExceptionError.class)
    public ResponseEntity<ValidationErrorDto> catchValidationException(ValidationExceptionError e) {
        return new ResponseEntity<>(
                new ValidationErrorDto(
                        e.getCode(),
                        e.getMessage(),
                        e.getErrors().stream().map(ve ->
                                        new ValidationFieldErrorDto(ve.getField(), ve.getMessage()))
                                .toList()
                ),
                //error code 422
                HttpStatus.UNPROCESSABLE_ENTITY
        );
    }
}
