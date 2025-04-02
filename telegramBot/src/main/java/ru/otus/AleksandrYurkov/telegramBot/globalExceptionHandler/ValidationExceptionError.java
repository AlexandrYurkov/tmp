package ru.otus.AleksandrYurkov.telegramBot.globalExceptionHandler;

import lombok.Getter;
import java.util.List;

@Getter
public class ValidationExceptionError extends RuntimeException {
    private String code;
    private List<ValidationFieldError> errors;
    public ValidationExceptionError(String code, String message, List<ValidationFieldError> errors) {
        super(message);
        this.code = code;
        this.errors = errors;
    }
}
