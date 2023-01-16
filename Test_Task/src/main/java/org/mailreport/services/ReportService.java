package org.mailreport.services;

import org.mailreport.Dao;
import org.mailreport.models.Mail;
import org.mailreport.models.Worker;
import org.mailreport.supportive.DataProcessor;
import org.mailreport.supportive.MyUtils;
import java.util.List;

public class ReportService {
    private Dao dao;

    public ReportService(Dao dao){
        this.dao = dao;
    }

    public void dailyReport(List<Worker> workers){
        DataProcessor dataProcessor = new DataProcessor(dao);

        workers.forEach(worker -> {
            Mail mail =
                    new Mail.MailBuilder()
                            .worker(worker)
                            .heading("DAILY REPORT")
                            .paragraph("The following site changes have taken place over the past 24 hours:")
                            .lineDelimiter(1)
                            .paragraph("Removed the following pages: {" + MyUtils.listToString(dataProcessor.getRemovedURLs()) + "}")
                            .lineDelimiter(1)
                            .paragraph("Added the following pages: {" + MyUtils.listToString(dataProcessor.getNewURLs()) + "}")
                            .lineDelimiter(1)
                            .paragraph("Changed the following pages: {" + MyUtils.listToString(dataProcessor.getChangedURLs()) + "}")
                            .lineDelimiter(1)
                            .paragraph("With care of you, monitoring system!")
                            .build();

            System.out.println(mail.print());
            //mail.send();
        });
    }
}