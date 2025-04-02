package ru.otus.AleksandrYurkov.telegramBot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.otus.AleksandrYurkov.telegramBot.entity.Orders;
import ru.otus.AleksandrYurkov.telegramBot.repository.OrderRepository;

import javax.sql.DataSource;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrdersService {
    private final OrderRepository orderRepository;

    public void creatOrders(Orders order) {
        orderRepository.save(order);
    }
    public Optional<Orders> findById(long id) {
        return orderRepository.findById(id);
    }
}
