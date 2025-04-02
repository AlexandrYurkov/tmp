package ru.otus.AleksandrYurkov.telegramBot.repository;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.AleksandrYurkov.telegramBot.entity.Master;

import java.util.List;
import java.util.Optional;

@Repository
public interface MasterRepository extends ListCrudRepository<Master, Long> {
    /*
        Написать два SQL запроса
        1) Возращает set мастеров из таблицы master_professional
         * Создать таблицу free_time (содержит поля дата, время, id_master)
         * Либо не создавать таблицу, а создать Set временных интервалов (каждые 30 например) и делать проверку в таблице orders
        2) Возращает set времени на определенную дату и у определенного мастера.
     */
}