package ru.otus.AleksandrYurkov.telegramBot.dto;

import lombok.NoArgsConstructor;
import ru.otus.AleksandrYurkov.telegramBot.entity.Customer;
import ru.otus.AleksandrYurkov.telegramBot.entity.Master;

import java.time.LocalDate;
import java.time.LocalTime;

public record OrderDTO(Customer customer, Master master, LocalDate date, LocalTime sessionTime) {
    //для подтверждения записи.
}
