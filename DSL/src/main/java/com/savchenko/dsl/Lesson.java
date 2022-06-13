package com.savchenko.dsl;

import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@Data
public class Lesson implements Comparable{
    private SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    private final Date date;

    @SneakyThrows
    public Lesson(String date){
        this.date = formatter.parse(date);
    }

    @Override
    public int compareTo(Object o) {
        if(!(o instanceof Lesson)) return 0;
        return date.compareTo(((Lesson)o).getDate());
    }
}

