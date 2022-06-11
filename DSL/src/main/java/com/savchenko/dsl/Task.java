package com.savchenko.dsl;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@Data
public class Task {
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private final String id;
    private final int points;
    private final Date deadLine;
    private final String title;

    @SneakyThrows
    public Task(String id, int points, String deadLine, String title) {
        this.id = id;
        this.points = points;
        this.deadLine = formatter.parse(deadLine);
        this.title = title;
    }
}
