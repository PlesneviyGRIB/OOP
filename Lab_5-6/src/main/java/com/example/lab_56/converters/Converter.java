package com.example.lab_56.converters;

import com.example.lab_56.dto.AirportDTO;
import com.example.lab_56.models.Airport;

public class Converter {
    public static AirportDTO airportToDTO(Airport airport){
        var dto = new AirportDTO();
        dto.city = airport.getCity();
        dto.code = airport.getCode();
        dto.name = airport.getName();
        dto.timezone = airport.getTimezone();

        return dto;
    }
}
