package ru.otus.AleksandrYurkov.telegramBot.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.otus.AleksandrYurkov.telegramBot.service.CustomerService;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/master")
public class MasterController {
    private final CustomerService customerService;
}
