package com.example.lab_56.controllers;

import com.example.lab_56.dto.AirportDTO;
import com.example.lab_56.dto.FlightScheduleDTO;
import com.example.lab_56.repositories.layers.FlightsRepositoryLayer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AirportsController extends BaseController{
    private final FlightsRepositoryLayer flightsRepositoryImpl;

    @GetMapping("/airports")
    public List<AirportDTO> all(@RequestParam(required = false) Long limit,
                                @RequestParam(required = false) Long page){
        return flightsRepositoryImpl.getAirports(limit, page);
    }

    @GetMapping("/airports/sources")
    public List<AirportDTO> sources(@RequestParam(required = false) Long limit,
                                    @RequestParam(required = false) Long page){
        return flightsRepositoryImpl.getSourcesAirports(limit, page);
    }

    @GetMapping("/airports/destinations")
    public List<AirportDTO> destinations(@RequestParam(required = false) Long limit,
                                         @RequestParam(required = false) Long page){
        return flightsRepositoryImpl.getDestinationAirports(limit, page);
    }

    @GetMapping("/airports/{cityName}")
    public List<AirportDTO> airportsByCity(@PathVariable("cityName") String name){
        return flightsRepositoryImpl.getAirportsByCity(name);
    }

    @GetMapping("/airports/{code}/inbound")
    public List<FlightScheduleDTO> inboundSchedule(@PathVariable String code){
        return flightsRepositoryImpl.inboundSchedule(code);
    }

    @GetMapping("/airports/{code}/outbound")
    public List<FlightScheduleDTO> outboundSchedule(@PathVariable String code){
        return flightsRepositoryImpl.outboundSchedule(code);
    }
}
