package com.example.lab_56.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("flights")
public class Flights {
    @Id
    @Column("flight_id")
    private Long id;
    @Column("flight_no")
    private String flightNo;
    @Column("scheduled_departure")
    private Timestamp scheduledDeparture;
    @Column("scheduled_arrival")
    private Timestamp scheduledArrival;
    @Column("departure_airport")
    private String departureAirport;
    @Column("arrival_airport")
    private String arrivalAirport;
    @Column("status")
    private String status;
    @Column("aircraft_code")
    private String aircraftCode;
    @Column("actual_departure")
    private String actualDeparture;
    @Column("actual_arrival")
    private String actualArrival;
}
