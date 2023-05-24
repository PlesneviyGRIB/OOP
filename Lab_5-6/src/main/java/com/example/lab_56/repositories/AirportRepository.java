package com.example.lab_56.repositories;

import com.example.lab_56.models.Airport;
import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;

public interface AirportRepository extends QuerydslJdbcRepository<Airport, String> {
    Boolean existsAirportByCity(String city);
}
