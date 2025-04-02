package ru.otus.AleksandrYurkov.telegramBot.entity;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import ru.otus.AleksandrYurkov.telegramBot.dto.CustomerDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "customer")
public class Customer {
    @Id
    private Long id;

    @Column(value = "lastname")
    private String lastname;

    @Column(value = "firstname")
    private String firstname;

    @Column(value = "telephone")
    private String telephone;

    @Column(value = "telegram_id")
    private String telegramId;

    @MappedCollection(idColumn = "customer_id")
    private List<Orders> orders = new ArrayList<>();

    public Customer(CustomerDTO customerDTO) {
        this.id = null;
        this.lastname = customerDTO.lastname();
        this.firstname = customerDTO.firstname();
        this.telephone = customerDTO.telephone();
        this.telegramId = customerDTO.telegramId();
    }
}