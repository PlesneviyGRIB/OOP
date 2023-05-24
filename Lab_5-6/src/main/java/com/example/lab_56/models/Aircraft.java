package com.example.lab_56.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("aircrafts")
public class Aircraft {
    @Id
    @Column("aircraft_code")
    private String code;
    @Column("model")
    private String model;
    @Column("range")
    private Integer range;
}
