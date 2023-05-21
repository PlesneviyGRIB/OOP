package com.example.lab_56.dto;

import java.sql.Timestamp;
import java.util.List;

public class FlightScheduleDTO {
    public List<DayTimeDTO> days;
    public String flightId;
    public AirportDTO origin;
    public AirportDTO destination;
}

class DayTimeDTO {
    public Integer day;
    public Timestamp departure;
}
