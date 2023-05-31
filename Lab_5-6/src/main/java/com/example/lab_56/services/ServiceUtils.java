package com.example.lab_56.services;

import com.example.lab_56.dto.FilterDTO;
import com.example.lab_56.dto.PartialRouteDTO;
import com.example.lab_56.dto.RouteDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.Calendar;
import java.util.Random;

public class ServiceUtils {
    public static void validateFilter(FilterDTO filter){
        if(filter.connections != null && (filter.connections > 5 || filter.connections < 0))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid connections count");

        if(filter.originAirportCode == null || filter.destinationAirportCode == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid airport code");

        if(filter.tripDuration < 1 || filter.tripDuration > 3)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid trip duration");

        if(filter.fareCondition == null ||
         !(filter.fareCondition.equals("Economy") ||
           filter.fareCondition.equals("Comfort") ||
           filter.fareCondition.equals("Business"))
        )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid fare condition");

        if(filter.departureDate == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid departureDate");

        if(filter.connections == null) filter.connections = 5;
    }

    public static void validatePartialRoute(PartialRouteDTO route){
        if(route.fareCondition == null ||
         !(route.fareCondition.equals("Economy") ||
           route.fareCondition.equals("Comfort") ||
           route.fareCondition.equals("Business"))
        )
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid fare condition");
    }

    public static Date addDays(Date date, int count){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, count);
        return new Date(cal.getTimeInMillis());
    }

    public static String generateKey(int length){
        int leftLimit = 48;
        int rightLimit = 122;
        Random random = new Random();

        return random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65) && (i <= 90 || i >= 97))
                .limit(length)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString()
                .toUpperCase();
    }
}
