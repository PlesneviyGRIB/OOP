package com.example.lab_56.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.sql.In;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("boarding_passes")
public class BoardingPass {
    @Column("ticket_no")
    private String ticketNo;
    @Id
    @Column("flight_id")
    private Long flightId;
    @Column("boarding_no")
    private Integer boardingNo;
    @Column("seat_no")
    private String seatNo;
}
