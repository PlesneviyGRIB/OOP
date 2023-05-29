package com.example.lab_56.services;

import com.example.lab_56.converters.Converter;
import com.example.lab_56.converters.Processor;
import com.example.lab_56.dto.AirportDTO;
import com.example.lab_56.dto.FlightScheduleDTO;
import com.example.lab_56.models.supportive.ScheduleRawLine;
import com.example.lab_56.repositories.AirportRepository;
import com.example.lab_56.repositories.FlightsRepository;
import com.example.lab_56.repositories.TicketFlightRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.example.lab_56.models.QAirport.airport;
import static com.example.lab_56.models.QFlights.flights;
import static com.example.lab_56.models.QSeat.seat;
import static com.example.lab_56.models.QTicketFlights.ticketFlights;
import static java.util.stream.Collectors.groupingBy;

@RequiredArgsConstructor
@Component
public class FlightsRepositoryLayer {
    private final FlightsRepository flightsRepository;
    private final AirportRepository airportRepository;
    private final TicketFlightRepository ticketFlightRepository;

    private static Processor<List<ScheduleRawLine>, List<FlightScheduleDTO>, Map<String, AirportDTO>> scheduleRawLineProcessor = (List<ScheduleRawLine> lines, Map<String, AirportDTO> airports) -> {
        record ScheduleLine(String flightNo, String originAirport, String destinationAirport, Integer dayOfWeek, String time){
            @Override
            public boolean equals(Object obj) {
                if(obj.getClass() != ScheduleLine.class) return false;

                ScheduleLine o = (ScheduleLine) obj;

                return o.flightNo.equals(this.flightNo) &&
                       o.originAirport.equals(this.originAirport) &&
                       o.destinationAirport.equals(this.destinationAirport) &&
                       o.time.equals(this.time);
            }
        }

        return lines.stream()
                .map(l -> new ScheduleLine(l.flightNo(), l.originAirport(), l.destinationAirport(), l.timestamp().getDay(), DateTimeFormatter.ofPattern("HH:mm:ss").format(l.timestamp().toLocalDateTime())))
                .distinct()
                .collect(groupingBy(l -> l.flightNo))
                .entrySet().stream()
                .map(entry -> {
                    var origin = entry.getValue().get(0).originAirport;
                    var destination = entry.getValue().get(0).destinationAirport;
                    return new FlightScheduleDTO(
                            entry.getKey(),
                            airports.get(origin),
                            airports.get(destination),
                            entry.getValue().stream().map(l -> new FlightScheduleDTO.DayTimeDTO(l.dayOfWeek, l.time)).sorted().toList()
                    );
                }).toList();
    };

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
        if(!airportRepository.existsAirportByCity(city))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such city!");

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

    public List<FlightScheduleDTO> inboundSchedule(String code){
        if(!airportRepository.existsById(code))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such airport!");

        var rawResult = airportRepository.query(q -> q
                .select(flights.flightNo, flights.departureAirport, flights.scheduledArrival)
                .distinct()
                .from(flights)
                .where(flights.arrivalAirport.eq(code).and(flights.status.eq("Scheduled")))
                .fetch());

        return scheduleRawLineProcessor.process(
                rawResult.stream().map(t -> new ScheduleRawLine(t.get(0, String.class), t.get(1, String.class), code, t.get(2, Timestamp.class))).toList(),
                airportRepository.findAll().stream().map(Converter::airportToDTO).collect(Collectors.toMap(AirportDTO::getCode, a -> a))
        );
    }

    public List<FlightScheduleDTO> outboundSchedule(String code){
        if(!airportRepository.existsById(code))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No such airport!");

        var rawResult = airportRepository.query(q -> q
                .select(flights.flightNo, flights.arrivalAirport, flights.scheduledDeparture)
                .distinct()
                .from(flights)
                .where(flights.departureAirport.eq(code).and(flights.status.eq("Scheduled")))
                .fetch());

        return scheduleRawLineProcessor.process(
                rawResult.stream().map(t -> new ScheduleRawLine(t.get(0, String.class), code, t.get(1, String.class), t.get(2, Timestamp.class))).toList(),
                airportRepository.findAll().stream().map(Converter::airportToDTO).collect(Collectors.toMap(AirportDTO::getCode, a -> a))
        );
    }

    public boolean isBookingAvailable(Long flightId, String fareCondition){
        var flight = flightsRepository.findById(flightId).get();

        var countOfSeats = flightsRepository.query(q -> q
                .select(seat.seatNo)
                .from(seat)
                .distinct()
                .where(seat.aircraftCode.eq(flight.getAircraftCode()).and(seat.fareCondition.eq(fareCondition)))
                .fetchCount()
        );

        System.out.println(countOfSeats);

        var countOfBoughtTickets = ticketFlightRepository.query(q -> q
                .select(ticketFlights.ticketNo)
                .from(ticketFlights)
                .distinct()
                .where(ticketFlights.flightId.eq(flightId).and(ticketFlights.fareCondition.eq(fareCondition)))
                .fetchCount()
        );

        System.out.println(countOfBoughtTickets);

        return countOfSeats - countOfBoughtTickets > 0;
    }
}