package com.savchenko.dsl;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Lesson {
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private final Date date;

    @SneakyThrows
    public Lesson(String date){
        this.date = formatter.parse(date);
    }
}
