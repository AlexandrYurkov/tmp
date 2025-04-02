package ru.otus.AleksandrYurkov.telegramBot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.AleksandrYurkov.telegramBot.entity.Master;
import ru.otus.AleksandrYurkov.telegramBot.entity.Profession;
import ru.otus.AleksandrYurkov.telegramBot.repository.CustomerRepository;
import ru.otus.AleksandrYurkov.telegramBot.repository.MasterRepository;
import ru.otus.AleksandrYurkov.telegramBot.repository.ProfessionRepository;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MasterService {
    private final MasterRepository masterRepository;
    private final ProfessionRepository professionRepository;

    /*
        создать 2 запроса в MasterRepository
        создать 2 getMapping на эти два запроса
        создать 1 postMapping для записи в orders (либо вынести это в отдельный OrdersService)
     */
    public List<Profession> getAllProfessions() {return professionRepository.findAll();}

    public Optional<Master> getMasterById(Long id) {return masterRepository.findById(id);}
}
