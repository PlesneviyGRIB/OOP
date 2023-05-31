package com.example.lab_56.controllers;

import com.example.lab_56.dto.FilterDTO;
import com.example.lab_56.dto.PartialRouteDTO;
import com.example.lab_56.dto.RouteDTO;
import com.example.lab_56.dto.TicketDTO;
import com.example.lab_56.services.RoutesService;
import com.example.lab_56.services.TicketService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class TicketsController extends BaseController{
    private final RoutesService routesService;
    private final TicketService ticketService;

    @PostMapping("/routes/filter")
    public List<RouteDTO> routes(@RequestBody FilterDTO filter){
        return routesService.routes(filter);
    }

    @PostMapping("/routes/booking")
    public String makeBooking(@RequestBody PartialRouteDTO route){
        return ticketService.makeBooking(route);
    }

    @GetMapping("/tickets/{passengerId}")
    public List<TicketDTO> checkTickets(@PathVariable String passengerId){
        return ticketService.getTickets(passengerId);
    }

    @PostMapping("/tickets/{ticketId}/checkin/{seatId}")
    public ResponseEntity<?> checkin(@PathVariable String ticketId,
                                     @PathVariable String seatId,
                                     @RequestBody String passengerId){
        ticketService.checkIn(ticketId, seatId, passengerId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/tickets/{ticketId}/checkin")
    public List<String> getSeats(@PathVariable String ticketId){
        return ticketService.getFreeSeats(ticketId);
    }
}