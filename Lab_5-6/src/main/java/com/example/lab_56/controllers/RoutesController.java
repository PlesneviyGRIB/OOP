package com.example.lab_56.controllers;

import com.example.lab_56.dto.FilterDTO;
import com.example.lab_56.dto.RouteDTO;
import com.example.lab_56.dto.TicketDTO;
import com.example.lab_56.services.RoutesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class RoutesController extends BaseController{
    private final RoutesService routesService;

    @PostMapping("/routes/filter")
    public List<RouteDTO> routes(@RequestBody FilterDTO filter){
        return routesService.routes(filter);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/routes/booking")
    public String makeBooking(@RequestBody RouteDTO route){
        return routesService.makeBooking(route);
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/tickets/{passengerId}")
    public List<TicketDTO> checkTickets(@PathVariable String passengerId){
        return routesService.getTickets(passengerId);
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/tickets/{id}/checkin")
    public boolean checkin(@PathVariable String id,
                        @RequestBody String passengerId){
        return routesService.checkIn(id, passengerId);
    }
}
