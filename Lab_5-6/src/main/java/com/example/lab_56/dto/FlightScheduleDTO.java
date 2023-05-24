package com.example.lab_56.dto;

import lombok.AllArgsConstructor;

import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
public class FlightScheduleDTO {
    @AllArgsConstructor
    public static class DayTimeDTO {
        public Integer day;
        public String departure;
    }
    public String flightId;
    public AirportDTO origin;
    public AirportDTO destination;
    public List<DayTimeDTO> days;
}