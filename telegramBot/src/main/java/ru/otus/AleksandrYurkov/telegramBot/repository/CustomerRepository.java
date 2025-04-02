package ru.otus.AleksandrYurkov.telegramBot.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.AleksandrYurkov.telegramBot.entity.Customer;
import ru.otus.AleksandrYurkov.telegramBot.entity.Orders;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends ListCrudRepository<Customer, Long> {

    //TODO добавить к таблице поле telegram_id
    @Query("SELECT * from customer where telegram_id = :telegramId and telephone =:telephone")
    Optional<Customer> findByTelegramId(String telegramId, String telephone);
}