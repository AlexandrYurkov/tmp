package ru.otus.AleksandrYurkov.telegramBot.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.AleksandrYurkov.telegramBot.entity.DateTime;
import ru.otus.AleksandrYurkov.telegramBot.repository.DateTimeRepository;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class DateTimeService {
    private final DateTimeRepository dateTimeRepository;

    public List<DateTime> getAllMastersById(Long masterId) {
        return dateTimeRepository.findAllByMasterId(masterId);
    }

    public Optional<DateTime> getDateTime(Long datetimeId) {
        return dateTimeRepository.findById(datetimeId);
    }

    public void delete(Long datetimeId) {
        dateTimeRepository.deleteById(datetimeId);
    }
}
