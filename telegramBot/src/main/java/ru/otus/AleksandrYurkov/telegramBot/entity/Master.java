package ru.otus.AleksandrYurkov.telegramBot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "master")
public class Master {
    @Id
    public Long id;

    @Column(value = "lastname")
    public String lastname;

    @Column(value = "firstname")
    public String firstname;

    @Column(value = "telephone")
    public String telephone;

    @MappedCollection(idColumn = "id")
    public Set<Profession> professions;

    @Transient
    @MappedCollection(idColumn = "master_id")
    private List<Orders> orders = new ArrayList<>();

}