package com.savchenko.dsl;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;


@Data
public class Task implements Comparable{
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

    @Override
    public int compareTo(Object o) {
        if(o instanceof ControlPoint) return deadLine.compareTo(((ControlPoint)o).getDate());
        if(!(o instanceof Task)) return 0;
        return deadLine.compareTo(((Task)o).getDeadLine());
    }
}
