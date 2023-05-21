package com.example.lab_56.repositories;

import com.example.lab_56.models.Flights;
import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;

public interface FlightsRepository extends QuerydslJdbcRepository<Flights, Long> {
}
