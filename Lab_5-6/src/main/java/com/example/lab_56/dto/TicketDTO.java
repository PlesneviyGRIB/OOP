package com.example.lab_56.dto;

import java.sql.Timestamp;

public class TicketDTO {
    public String id;
    public Timestamp departure;
    public Timestamp arrival;
    public TicketInfoDTO info;
    public Long price;
    public AirportDTO origin;
    public AirportDTO destination;
}
