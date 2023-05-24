package com.example.lab_56.models.supportive;

import java.sql.Timestamp;

public record ScheduleRawLine(String flightNo, String originAirport, String destinationAirport, Timestamp timestamp){}
