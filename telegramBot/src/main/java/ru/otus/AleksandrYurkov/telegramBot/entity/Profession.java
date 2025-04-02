package ru.otus.AleksandrYurkov.telegramBot.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "profession")
public class Profession {
    @Id
    public Long id;

    @Column(value = "name")
    public String name;

    @Transient
    @MappedCollection(idColumn = "id")
    public Set<Master> masters = new HashSet<>();

}