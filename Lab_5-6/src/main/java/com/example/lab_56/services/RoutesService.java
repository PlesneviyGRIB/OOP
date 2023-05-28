package com.example.lab_56.services;

import com.example.lab_56.dto.FilterDTO;
import com.example.lab_56.dto.RouteDTO;
import com.example.lab_56.models.Booking;
import com.example.lab_56.models.Route;
import com.example.lab_56.repositories.BookingRepository;
import com.example.lab_56.repositories.FlightsRepository;
import com.example.lab_56.repositories.TicketFlightRepository;
import com.example.lab_56.repositories.TicketRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class RoutesService {
    private final FlightsRepository flightsRepository;
    private final FlightsRepositoryLayer flightsRepositoryLayer;
    private final BookingRepository bookingRepository;
    private final TicketRepository ticketRepository;
    private final TicketFlightRepository ticketFlightRepository;

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

        return res.stream().map(r -> {
            String[] flights = r.getRouteSequence().split("->");
            var dto = new RouteDTO();
            dto.flights = new ArrayList<>(Arrays.asList(flights));
            dto.price = r.getAmount();
            dto.fareCondition = filter.fareCondition;
            return dto;
        }).collect(Collectors.toList());
    }

    public String makeBooking(RouteDTO route){
        ServiceUtils.validateRoute(route);

        route.flights.stream()
                .map(Long::parseLong)
                .filter(f ->  !flightsRepositoryLayer.isBookingAvailable(f, route.fareCondition))
                .findAny().map(f -> {throw new ResponseStatusException(HttpStatus.CONFLICT, "Unable to book due to lack of tickets for flight: '" + f + "' with fare condition: '" + route.fareCondition + "'");});

        var bookingId = ServiceUtils.generateKey(6);
        bookingRepository.manualSave(
                bookingId,
                new Date(System.currentTimeMillis()),
                route.price
        );

        var passengerId = ServiceUtils.generateKey(20);
        route.flights.stream()
                .map(Long::parseLong)
                .forEach(f -> {
                    var ticketId = ServiceUtils.generateKey(13);
                    ticketRepository.manualSave(
                            ticketId,
                            bookingId,
                            passengerId,
                            "Ivan",
                            "{\"phone\": \"+79137771428\"}"
                    );
                    ticketFlightRepository.manualSave(ticketId, f, route.fareCondition, 0L);
                });

        return passengerId;
    }
}