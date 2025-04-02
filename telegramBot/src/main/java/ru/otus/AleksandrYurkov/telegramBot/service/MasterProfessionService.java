package ru.otus.AleksandrYurkov.telegramBot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.AleksandrYurkov.telegramBot.entity.Master;
import ru.otus.AleksandrYurkov.telegramBot.entity.MasterProfession;
import ru.otus.AleksandrYurkov.telegramBot.repository.MasterProfessionRepository;
import ru.otus.AleksandrYurkov.telegramBot.repository.MasterRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MasterProfessionService {
    private final MasterProfessionRepository masterProfessionRepository;

    public List<Long> getAllMastersById(Long professionId) {
        return masterProfessionRepository.getAllMasters(professionId);
    }


}
