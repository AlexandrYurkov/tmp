package ru.otus.AleksandrYurkov.telegramBot.repository;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import ru.otus.AleksandrYurkov.telegramBot.entity.Orders;

import javax.sql.DataSource;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends ListCrudRepository<Orders, Long> {

//        String checkCustomerSql = "SELECT COUNT(*) FROM customer WHERE id = ?";
//        String checkMasterSql = "SELECT COUNT(*) FROM master WHERE id = ?";
//        String insertSql = "INSERT INTO orders (customer_id, master_id, date_time) VALUES (?, ?, ?) RETURNING id";


}