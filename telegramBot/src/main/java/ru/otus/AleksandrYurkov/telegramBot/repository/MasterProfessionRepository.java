package ru.otus.AleksandrYurkov.telegramBot.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.AleksandrYurkov.telegramBot.entity.Customer;
import ru.otus.AleksandrYurkov.telegramBot.entity.Master;

import java.util.List;

@Repository
public interface MasterProfessionRepository extends ListCrudRepository<Customer, Long> {
    @Query("SELECT master_id from master_profession WHERE profession_id = :professionId")
    List<Long> getAllMasters(@Param("professionId") Long professionId);
}
