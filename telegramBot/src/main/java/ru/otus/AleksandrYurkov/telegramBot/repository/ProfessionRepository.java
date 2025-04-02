package ru.otus.AleksandrYurkov.telegramBot.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.AleksandrYurkov.telegramBot.entity.Profession;


@Repository
public interface ProfessionRepository extends ListCrudRepository<Profession, Long> {


}