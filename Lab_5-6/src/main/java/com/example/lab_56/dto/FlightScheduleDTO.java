package com.example.lab_56.dto;

import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class FlightScheduleDTO {
    @AllArgsConstructor
    public static class DayTimeDTO implements Comparable<DayTimeDTO>{
        public Integer day;
        public String departure;

        @Override
        public int compareTo(DayTimeDTO o) {
            return this.day.compareTo(o.day);
        }
    }
    public String flightId;
    public AirportDTO origin;
    public AirportDTO destination;
    public List<DayTimeDTO> days;
}