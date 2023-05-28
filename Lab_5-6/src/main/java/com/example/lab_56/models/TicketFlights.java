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
@Table("ticket_flights")
public class TicketFlights {
    @Column("ticket_no")
    private String ticketNo;
    @Id
    @Column("flight_id")
    private Long flightId;
    @Column("fare_conditions")
    private String fareCondition;
    @Column("amount")
    private Long amount;
}
