package ru.otus.AleksandrYurkov.telegramBot.dto;

import ru.otus.AleksandrYurkov.telegramBot.entity.Master;

import java.time.LocalDate;
import java.util.Set;

public record ProfessionDTO(Set<Master> masters) {
    //для запроса по определенной профессии
}