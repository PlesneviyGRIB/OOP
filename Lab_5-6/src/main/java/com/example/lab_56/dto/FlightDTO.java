package com.example.lab_56.dto;

import java.sql.Timestamp;

public class FlightDTO {
    public AirportDTO origin;
    public AirportDTO destination;
    public Timestamp departure;
    public Timestamp arrival;
    public String aircraftCode;
    public Long price;
}