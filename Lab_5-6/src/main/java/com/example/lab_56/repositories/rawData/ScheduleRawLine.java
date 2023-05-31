package com.example.lab_56.repositories.rawData;

import java.sql.Timestamp;

public record ScheduleRawLine(String flightNo, String originAirport, String destinationAirport, Timestamp timestamp){}
