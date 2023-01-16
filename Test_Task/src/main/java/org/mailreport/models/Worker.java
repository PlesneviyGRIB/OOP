package org.mailreport.models;

import lombok.Getter;
import org.mailreport.supportive.MyUtils;

@Getter
public class Worker {
    private final String name;
    private final String surname;
    private final String email;

    public Worker(String name, String surname, String email) throws Exception{
        this.name = name;
        this.surname = surname;

        if(!MyUtils.isEmail(email)) throw new Exception("Wrong email");
        this.email = email;
    }

    public static Worker phonyWorker(){
        try {
            return new Worker("bot", "bot", "inappropriate@gmail.com");
        } catch (Exception ignore){}

        return null;
    }
}
