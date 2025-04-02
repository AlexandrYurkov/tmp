package ru.otus.AleksandrYurkov.telegramBot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "data_time")
public class DateTime {
    @Id
    @Column (value = "id")
    private Long id;
    @Column (value = "date")
    private String date;
    @Column (value = "master_id")
    @MappedCollection(idColumn = "id")
    private Long masterId;
}
