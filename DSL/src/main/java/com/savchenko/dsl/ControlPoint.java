package com.savchenko.dsl;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class ControlPoint implements Comparable{
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private final String controlPointName;
    private final Date date;

    @SneakyThrows
    public ControlPoint(String controlPointName, String date){
        this.controlPointName = controlPointName;
        this.date = formatter.parse(date);
    }

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof ControlPoint)) return 0;
        return date.compareTo(((ControlPoint)o).getDate());
    }
}
