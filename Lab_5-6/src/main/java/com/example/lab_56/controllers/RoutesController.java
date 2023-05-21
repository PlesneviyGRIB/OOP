package com.example.lab_56.controllers;

import com.example.lab_56.dto.FilterDTO;
import com.example.lab_56.dto.RouteDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class RoutesController extends BaseController{
    @PostMapping("/routes/filter")
    public List<RouteDTO> routes(@RequestBody FilterDTO filter){
        return Collections.emptyList();
    }

    @PostMapping("/routes/{id}")
    public void makeBooking(@PathVariable Long id){
    }
}
