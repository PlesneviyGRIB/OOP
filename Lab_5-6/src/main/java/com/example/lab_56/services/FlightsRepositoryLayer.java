package com.example.lab_56.services;

import com.example.lab_56.converters.Converter;
import com.example.lab_56.dto.AirportDTO;
import com.example.lab_56.repositories.AirportRepository;
import com.example.lab_56.repositories.FlightsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.lab_56.models.QAirport.airport;
import static com.example.lab_56.models.QFlights.flights;

@RequiredArgsConstructor
@Component
public class FlightsRepositoryLayer {
    private final FlightsRepository flightsRepository;
    private final AirportRepository airportRepository;

    public List<String> getCities(Long limit, Long page){
        return flightsRepository.query(q -> q
                        .select(airport.city)
                        .from(airport)
                        .offset(limit * page)
                        .limit(limit)
                        .orderBy(airport.city.asc())
                        .fetch()
                );
    }

    public List<String> getSourcesCities(Long limit, Long page){
        return flightsRepository.query(q -> q
                        .select(airport.city)
                        .distinct()
                        .from(flights)
                        .join(airport).on(airport.code.eq(flights.departureAirport))
                        .offset(limit * page)
                        .limit(limit)
                        .orderBy(airport.city.asc())
                        .fetch()
                );
    }

    public List<String> getDestinationsCities(Long limit, Long page){
        return flightsRepository.query(q -> q
                        .select(airport.city)
                        .distinct()
                        .from(flights)
                        .join(airport).on(airport.code.eq(flights.arrivalAirport))
                        .offset(limit * page)
                        .limit(limit)
                        .orderBy(airport.city.asc())
                        .fetch()
                );
    }

    public List<AirportDTO> getAirports(Long limit, Long page){
        var airports = airportRepository.query(q -> q
                .select(airportRepository.entityProjection())
                .from(airport)
                .offset(limit * page)
                .limit(limit)
                .orderBy(airport.code.asc())
                .fetch()
        );

        return airports
                .stream()
                .map(Converter::airportToDTO)
                .collect(Collectors.toList());
    }

    public List<AirportDTO> getSourcesAirports(Long limit, Long page){
        var airports = airportRepository.query(q -> q
                .select(airportRepository.entityProjection())
                .distinct()
                .from(flights)
                .join(airport).on(airport.code.eq(flights.departureAirport))
                .offset(limit * page)
                .limit(limit)
                .orderBy(airport.code.asc())
                .fetch()
        );

        return airports
                .stream()
                .map(Converter::airportToDTO)
                .collect(Collectors.toList());
    }

    public List<AirportDTO> getDestinationAirports(Long limit, Long page){
        var airports = airportRepository.query(q -> q
                .select(airportRepository.entityProjection())
                .distinct()
                .from(flights)
                .join(airport).on(airport.code.eq(flights.arrivalAirport))
                .offset(limit * page)
                .limit(limit)
                .orderBy(airport.code.asc())
                .fetch()
        );

        return airports
                .stream()
                .map(Converter::airportToDTO)
                .collect(Collectors.toList());
    }

    public List<AirportDTO> getAirportsByCity(String city){
        var airports = airportRepository.query(q -> q
                .select(airportRepository.entityProjection())
                .from(airport)
                .where(airport.city.eq(city))
                .orderBy(airport.code.asc())
                .fetch()
        );

        return airports
                .stream()
                .map(Converter::airportToDTO)
                .collect(Collectors.toList());
    }
}
