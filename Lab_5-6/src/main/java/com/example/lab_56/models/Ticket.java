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
@Table("Tickets")
public class Ticket {
    @Id
    @Column("ticket_no")
    private String ticketNo;
    @Column("book_ref")
    private String bookRef;
    @Column("passenger_id")
    private String passengerId;
    @Column("passenger_name")
    private String passengerName;
    @Column("contact_data")
    private String contactData;
}
