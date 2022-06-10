package com.savchenko.dsl;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class IncomingTask {
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private final String id;

    @SneakyThrows
    public IncomingTask(String id, String date) {
        this.id = id;
        this.date = formatter.parse(date);
    }

    private final Date date;
}
