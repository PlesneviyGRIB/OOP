package org.mailreport;


import org.mailreport.models.Worker;
import org.mailreport.services.ReportService;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws Exception{

        ReportService reportService = new ReportService(new Dao());

        List<Worker> workers = new ArrayList<>();
        workers.add(new Worker("Irina", "Sidorova", "hr@gmail.com"));

        reportService.dailyReport(workers);
    }
}