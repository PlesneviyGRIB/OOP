package com.example.lab_56.repositories;

import com.example.lab_56.models.Flight;
import com.infobip.spring.data.jdbc.QuerydslJdbcRepository;
import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;

public interface FlightsRepository extends QuerydslJdbcRepository<Flight, Long> {
    @Query("""
            with recursive node AS (
                select f.departure_airport, f.arrival_airport, f.scheduled_arrival, cast(f.flight_id as varchar(50)) as route, 0 as count
                from flights as f
                join ticket_flights tf on f.flight_id = tf.flight_id
                  where f.status = 'Scheduled'
                  and tf.fare_conditions = :fareCondition
                  and date(f.scheduled_departure) > :departureDate
                  and date(f.scheduled_arrival) < :arrivalDate
                  and departure_airport = :origin
                union
                select f.departure_airport, f.arrival_airport, f.scheduled_arrival, cast(n.route || '->' || f.flight_id as varchar(50)), n.count + 1
                from node as n
                join flights as f on f.departure_airport = n.arrival_airport
                join ticket_flights tf on f.flight_id = tf.flight_id
                  where f.status = 'Scheduled'
                    and tf.fare_conditions = :fareCondition
                    and date(f.scheduled_departure) > :departureDate
                    and date(f.scheduled_arrival) < :arrivalDate
                    and f.arrival_airport != n.departure_airport
                    and f.departure_airport != :destination
                    and n.scheduled_arrival < f.scheduled_departure
                  and count < :transfersCount
            )
            select n.route
            from node as n
            where n.arrival_airport = :destination;
            """)
    List<String> getRoutes(@Param("departureDate") Date departureDate,
                           @Param("arrivalDate") Date arrivalDate,
                           @Param("origin") String originAirportCode,
                           @Param("destination") String destinationAirportCode,
                           @Param("fareCondition") String fareCondition,
                           @Param("transfersCount") int transfersCount
    );
}