package com.savchenko.dsl;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ControlPoint {
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private final String controlPointName;
    private final Date date;

    @SneakyThrows
    public ControlPoint(String controlPointName, String date){
        this.controlPointName = controlPointName;
        this.date = formatter.parse(date);
    }
}
