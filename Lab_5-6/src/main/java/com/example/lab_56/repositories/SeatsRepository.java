package com.example.lab_56.repositories;

import com.example.lab_56.models.Seat;
import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;

public interface SeatsRepository extends QuerydslJdbcRepository<Seat, String> {
}
