package ru.otus.AleksandrYurkov.telegramBot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders")
public class Orders {
    @Id
    public Long id;


    @MappedCollection(idColumn = "id")
    @Column(value = "customer_id")
    public Customer customer;


    @MappedCollection(idColumn = "id")
    @Column(value = "master_id")
    public Master master;

    @Column(value = "date_time")
    public String dateTime;

}