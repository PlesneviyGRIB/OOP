package com.example.lab_56.converters;

import com.example.lab_56.dto.AirportDTO;
import com.example.lab_56.models.Airport;

import java.util.Map;

@FunctionalInterface
public
interface Processor<T,D,K extends Map<String, AirportDTO>> {
    D process(T val, K map);
}