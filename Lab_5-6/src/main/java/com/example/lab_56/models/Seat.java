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
@Table("seats")
public class Seat {
    @Column("aircraft_code")
    private String aircraftCode;
    @Id
    @Column("seat_no")
    private String seatNo;
    @Column("fare_conditions")
    private String fareCondition;
}