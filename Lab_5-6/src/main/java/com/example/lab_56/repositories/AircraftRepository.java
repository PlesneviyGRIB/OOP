package com.example.lab_56.repositories;

import com.example.lab_56.models.Aircraft;
import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;

public interface AircraftRepository extends QuerydslJdbcRepository<Aircraft, Long> {
}
