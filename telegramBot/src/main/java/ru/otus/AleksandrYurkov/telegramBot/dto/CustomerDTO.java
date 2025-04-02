package ru.otus.AleksandrYurkov.telegramBot.dto;

import ru.otus.AleksandrYurkov.telegramBot.entity.Orders;

import java.util.Set;

public record CustomerDTO(String lastname, String firstname,
                                String telephone, String telegramId) {
    // Для регистрации пользователя
}

