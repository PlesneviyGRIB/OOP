package com.savchenko.spring.models;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "log")
@ToString
@Getter @Setter
public class LogRecord implements Comparable<LogRecord>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String ip;
    private String userAgent;
    private Timestamp timeStamp;
    private int bannerId;
    private String categories;
    private double price;

    public LogRecord(){}

    public LogRecord(String ip, String userAgent, int bannerId, String categories, double price) {
        this.ip = ip;
        this.userAgent = userAgent;
        this.bannerId = bannerId;
        this.categories = categories;
        this.price = price;
    }

    @Override
    public int compareTo(LogRecord o) {
        Timestamp t1 = this.timeStamp;
        Timestamp t2 = o.getTimeStamp();
        return t1.compareTo(t2);
    }
}
