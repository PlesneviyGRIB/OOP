package com.example.lab_56.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AirportDTO {
    public String code;
    public String name;
    public String city;
    public String timezone;
}