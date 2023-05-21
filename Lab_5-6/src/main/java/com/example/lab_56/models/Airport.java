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
@Table("airports")
public class Airport {
    @Id
    @Column("airport_code")
    private String code;
    @Column("airport_name")
    private String name;
    @Column("city")
    private String city;
    @Column("longitude")
    private Double longitude;
    @Column("latitude")
    private Double latitude;
    @Column("timezone")
    private String timezone;
}
