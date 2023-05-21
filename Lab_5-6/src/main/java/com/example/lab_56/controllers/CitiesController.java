package com.example.lab_56.controllers;

import com.example.lab_56.services.FlightsRepositoryLayer;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CitiesController extends BaseController {
    private final FlightsRepositoryLayer flightsRepositoryImpl;

    @GetMapping("/cities")
    public List<String> all(@RequestParam(required = false) Long limit,
                            @RequestParam(required = false) Long page){
        return flightsRepositoryImpl.getCities(limit, page);
    }

    @GetMapping("/cities/sources")
    public List<String> sources(@RequestParam(required = false) Long limit,
                                @RequestParam(required = false) Long page){
        return flightsRepositoryImpl.getSourcesCities(limit, page);
    }

    @GetMapping("/cities/destinations")
    public List<String> destinations(@RequestParam(required = false) Long limit,
                                     @RequestParam(required = false) Long page){
        return flightsRepositoryImpl.getDestinationsCities(limit, page);
    }
}
