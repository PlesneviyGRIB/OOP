package com.example.lab_56.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
@AllArgsConstructor
public class FlightDTO {
    public Long flightId;
    public AirportDTO origin;
    public AirportDTO destination;
    public Timestamp departure;
    public Timestamp arrival;
    public String aircraftCode;
    public List<Long> prices;
}