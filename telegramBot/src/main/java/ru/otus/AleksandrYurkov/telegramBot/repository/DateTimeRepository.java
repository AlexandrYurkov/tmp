package ru.otus.AleksandrYurkov.telegramBot.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.otus.AleksandrYurkov.telegramBot.entity.DateTime;

import java.util.List;

@Repository
public interface DateTimeRepository extends ListCrudRepository<DateTime, Long> {
    @Query("Select * from data_time where master_id = :masterId order by id")
    List<DateTime> findAllByMasterId(@Param("masterId") Long masterId);

}
