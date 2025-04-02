package ru.otus.AleksandrYurkov.telegramBot.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "master_profession")
public class MasterProfession {
    @Column(value = "master_id")
    @MappedCollection(idColumn = "id")
    public Master master;

    @Column(value = "profession_id")
    @MappedCollection(idColumn = "id")
    public Profession profession;

    @Column(value = "master_id")
    public Long masterId;

    @Column(value = "profession_id")
    public Long professionId;

}