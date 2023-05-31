package com.example.lab_56.services;

import com.example.lab_56.dto.PartialRouteDTO;
import com.example.lab_56.dto.TicketDTO;
import com.example.lab_56.repositories.*;
import com.example.lab_56.repositories.layers.FlightsRepositoryLayer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.example.lab_56.models.QBoardingPass.boardingPass;
import static com.example.lab_56.models.QSeat.seat;
import static com.example.lab_56.models.QTicket.ticket;
import static com.example.lab_56.models.QTicketFlights.ticketFlights;

@RequiredArgsConstructor
@Service
public class TicketService {
    private final FlightsRepositoryLayer flightsRepositoryLayer;
    private final FlightsRepository flightsRepository;
    private final BookingRepository bookingRepository;
    private final TicketRepository ticketRepository;
    private final TicketFlightRepository ticketFlightRepository;
    private final BoardingPassRepository boardingPassRepository;

    public String makeBooking(PartialRouteDTO route){
        ServiceUtils.validatePartialRoute(route);

        route.partialFlights.stream()
                .map(pf -> pf.flightId)
                .filter(f ->  !flightsRepositoryLayer.isBookingAvailable(f, route.fareCondition))
                .findAny().map(f -> {throw new ResponseStatusException(HttpStatus.CONFLICT, "Unable to book due to lack of tickets for flight: '" + f + "' with fare condition: '" + route.fareCondition + "'");});

        var bookingId = ServiceUtils.generateKey(6);
        bookingRepository.manualSave(
                bookingId,
                new Date(System.currentTimeMillis()),
                route.partialFlights.stream().map(pf -> pf.price).mapToLong(Long::valueOf).sum()
        );

        var passengerId = ServiceUtils.generateKey(20);
        route.partialFlights
                .forEach(pf -> {
                    var ticketId = ServiceUtils.generateKey(13);
                    ticketRepository.manualSave(
                            ticketId,
                            bookingId,
                            passengerId,
                            "Ivan",
                            "{\"phone\": \"+79137771428\"}"
                    );
                    ticketFlightRepository.manualSave(ticketId, pf.flightId, route.fareCondition, pf.price);
                });
        return passengerId;
    }

    public List<TicketDTO> getTickets(String passengerId){
        return ticketRepository.getTicketsByPassengerId(passengerId);
    }

    public void checkIn(String ticketId, String seatId, String passengerId){
        var ticketRaw = ticketRepository.query(q -> q.select(ticket.ticketNo, ticket.passengerId).from(ticket).where(ticket.ticketNo.eq(ticketId)).fetchFirst());

        if(!Objects.equals(ticketRaw.get(1, String.class), passengerId))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "Passenger in not owner!");

        var ticketFlight = ticketFlightRepository.getTicketFlightsByTicketNo(ticketId).get();

        if(boardingPassRepository.existsById(ticketFlight.getFlightId()))
            throw new ResponseStatusException(HttpStatus.ACCEPTED, "You already checked in!");

        if(!getFreeSeats(ticketId).contains(seatId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such free seat: " + seatId + "for flight: " + ticketFlight.getFlightId());

        boardingPassRepository.manualSave(
                ticketRaw.get(0, String.class),
                ticketFlight.getFlightId(),
                0,
                seatId
        );
    }

    public List<String> getFreeSeats(String ticketId){
        var ticketFlight =  ticketFlightRepository.query(q -> q
                .select(ticketFlightRepository.entityProjection())
                .from(ticketFlights)
                .where(ticketFlights.ticketNo.eq(ticketId))
                .fetchOne()
        );

        var flight = flightsRepository.findById(ticketFlight.getFlightId()).get();

        var seats = flightsRepository.query(q -> q
                .select(seat.seatNo)
                .from(seat)
                .where(seat.fareCondition.eq(ticketFlight.getFareCondition()).and(seat.aircraftCode.eq(flight.getAircraftCode())))
                .fetch()
        );

        var busySeats = boardingPassRepository.query(q -> q
                .select(boardingPass.seatNo)
                .from(boardingPass)
                .where(boardingPass.ticketNo.eq(ticketId).and(boardingPass.flightId.eq(flight.getId())))
                .fetchOne()
        );

        seats.removeAll(Collections.singleton(busySeats));

        return seats;
    }
}
