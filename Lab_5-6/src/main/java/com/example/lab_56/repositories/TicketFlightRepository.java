package com.example.lab_56.repositories;

import com.example.lab_56.models.TicketFlights;
import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;
import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface TicketFlightRepository extends QuerydslJdbcRepository<TicketFlights, String> {
    @Modifying
    @Query("insert into ticket_flights (ticket_no, flight_id, fare_conditions, amount) VALUES (:ticket_no, :flight_id, :fare_conditions, :amount)")
    void manualSave(@Param("ticket_no") String ticket_no,
                    @Param("flight_id") Long flight_id,
                    @Param("fare_conditions") String fare_conditions,
                    @Param("amount") Long amount);

    Optional<TicketFlights> getTicketFlightsByTicketNo(String ticketNo);
}
