package ru.otus.AleksandrYurkov.telegramBot.globalExceptionHandler;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ValidationFieldErrorDto {
    private String field;
    private String message;
}
