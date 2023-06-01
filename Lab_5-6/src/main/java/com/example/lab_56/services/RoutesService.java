package com.example.lab_56.services;

import com.example.lab_56.dto.FilterDTO;
import com.example.lab_56.dto.FlightDTO;
import com.example.lab_56.dto.RouteDTO;
import com.example.lab_56.repositories.FlightsRepository;
import com.example.lab_56.repositories.layers.FlightsRepositoryLayer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoutesService {
    private final FlightsRepository flightsRepository;
    private final FlightsRepositoryLayer flightsRepositoryLayer;

    public List<RouteDTO> routes(FilterDTO filter){
        ServiceUtils.validateFilter(filter);

        var res = flightsRepository.getRoutes(
                ServiceUtils.addDays(filter.departureDate, -1),
                ServiceUtils.addDays(filter.departureDate, filter.tripDuration),
                filter.originAirportCode,
                filter.destinationAirportCode,
                filter.fareCondition,
                filter.connections
        );

        var flightIds = res.stream().flatMap(r -> Arrays.stream(r.split("->"))).map(Long::parseLong).distinct().toList();
        var flightsMap = flightsRepositoryLayer.getFlightDTOs(flightIds, filter.fareCondition).stream().collect(Collectors.toMap(FlightDTO::getFlightId, f -> f));

        return res.stream().map(r -> {
                    var currentFlightIds = Arrays.stream(r.split("->")).map(Long::parseLong).toList();
                    var dto = new RouteDTO();
                    dto.flights = currentFlightIds.stream().map(flightsMap::get).toList();
                    dto.price = dto.flights.stream().map(f -> f.prices.get(0)).mapToLong(Long::longValue).sum();
                    dto.fareCondition = filter.fareCondition;
                    return dto;
                })
                .filter(r -> r.flights.stream().map(f -> f.origin.city).allMatch(new HashSet<>()::add))
                .sorted(Comparator.comparing(r -> r.price))
                .collect(Collectors.toList());
    }
}