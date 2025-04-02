package ru.otus.AleksandrYurkov.telegramBot.globalExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationFieldError {
    private String field;
    private String message;

}
