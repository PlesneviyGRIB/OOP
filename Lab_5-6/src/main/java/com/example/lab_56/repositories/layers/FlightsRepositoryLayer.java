package com.example.lab_56.repositories.layers;

import com.example.lab_56.converters.Converter;
import com.example.lab_56.converters.Processor;
import com.example.lab_56.dto.AirportDTO;
import com.example.lab_56.dto.FlightDTO;
import com.example.lab_56.dto.FlightScheduleDTO;
import com.example.lab_56.repositories.rawData.ScheduleRawLine;
import com.example.lab_56.repositories.AirportRepository;
import com.example.lab_56.repositories.FlightsRepository;
import com.example.lab_56.repositories.TicketFlightRepository;
import com.querydsl.core.BooleanBuilder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import static com.example.lab_56.models.QAirport.airport;
import static com.example.lab_56.models.QFlight.flight;
import static com.example.lab_56.models.QPriceInfo.priceInfo;
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
                        .from(flight)
                        .join(airport).on(airport.code.eq(flight.departureAirport))
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
                        .from(flight)
                        .join(airport).on(airport.code.eq(flight.arrivalAirport))
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
                .from(flight)
                .join(airport).on(airport.code.eq(flight.departureAirport))
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
                .from(flight)
                .join(airport).on(airport.code.eq(flight.arrivalAirport))
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
                .select(flight.flightNo, flight.departureAirport, flight.scheduledArrival)
                .distinct()
                .from(flight)
                .where(flight.arrivalAirport.eq(code).and(flight.status.eq("Scheduled")))
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
                .select(flight.flightNo, flight.arrivalAirport, flight.scheduledDeparture)
                .distinct()
                .from(flight)
                .where(flight.departureAirport.eq(code).and(flight.status.eq("Scheduled")))
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

        var countOfBoughtTickets = ticketFlightRepository.query(q -> q
                .select(ticketFlights.ticketNo)
                .from(ticketFlights)
                .distinct()
                .where(ticketFlights.flightId.eq(flightId).and(ticketFlights.fareCondition.eq(fareCondition)))
                .fetchCount()
        );

        return countOfSeats - countOfBoughtTickets > 0;
    }

    public List<FlightDTO> getFlightDTOs(List<Long> flightIds, String fareCondition){
        BooleanBuilder predicate = new BooleanBuilder(priceInfo.fareCondition.eq(fareCondition));
        if(fareCondition.equals("Economy")){
            predicate.or(priceInfo.fareCondition.eq("Economy_+"));
        }
        var rawResult = flightsRepository.query(q -> q
                .select(flight.id,
                        flight.scheduledDeparture,
                        flight.scheduledArrival,
                        flight.departureAirport,
                        flight.arrivalAirport,
                        flight.aircraftCode,
                        priceInfo.amount)
                .distinct()
                .from(flight)
                .join(priceInfo).on(
                        priceInfo.aircraftCode.eq(flight.aircraftCode)
                        .and(priceInfo.origin.eq(flight.departureAirport))
                        .and(priceInfo.destination.eq(flight.arrivalAirport)))
                .where(predicate.and(flight.id.in(flightIds)))
                .fetch());

        var airportIds = rawResult.stream().flatMap(row -> Stream.of(row.get(3, String.class), row.get(4, String.class))).distinct().toList();

        var airportsMap = StreamSupport.stream(airportRepository.findAllById(airportIds).spliterator(), false)
                .map(a -> new AirportDTO(a.getCode(), a.getName(), a.getCity(), a.getTimezone()))
                .collect(Collectors.toMap(AirportDTO::getCode, a -> a));

        return rawResult.stream()
                .collect(groupingBy(row -> new FlightDTO(
                    row.get(0, Long.class),
                    airportsMap.get(row.get(3, String.class)),
                    airportsMap.get(row.get(4, String.class)),
                    row.get(1, Timestamp.class),
                    row.get(2, Timestamp.class),
                    row.get(5, String.class),
                    Collections.emptyList()))
                )
                .entrySet().stream()
                .map(e -> {
                    var dto = e.getKey();
                    dto.prices = e.getValue().stream().map(v -> v.get(6, Long.class)).toList();
                    return dto;
                })
                .toList();
    }
}