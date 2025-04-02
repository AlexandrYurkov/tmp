package ru.otus.AleksandrYurkov.telegramBot.dto;

import ru.otus.AleksandrYurkov.telegramBot.entity.Orders;
import ru.otus.AleksandrYurkov.telegramBot.entity.Profession;

import java.util.Set;

public record MasterDTO(String lastname, String firstname, String telephone) {
    //Для добавления в бд. Возможно надо добавить сет профессий
}