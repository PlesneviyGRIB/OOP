package org.mailreport.models;

import lombok.Getter;

import java.util.LinkedList;
import java.util.List;

@Getter
public class Mail {
    private final String heading;
    private final List<String> body;
    private final Worker worker;

    private Mail(String heading, List<String> body, Worker worker){
        this.heading = heading;
        this.body = body;
        this.worker = worker;
    }

    public String print(){
        StringBuilder stringBuilder = new StringBuilder();

        String sep = System.getProperty("line.separator");

        stringBuilder.append("<").append(heading).append(">").append(sep);

        stringBuilder.append("Hello, ").append(worker.getName()).append(" ").append(worker.getSurname()).append(sep);

        if(body.size() == 0)
            stringBuilder.append("no content");
        else
            body.forEach(s ->
                stringBuilder.append(s).append(sep)
            );

        return stringBuilder.toString();
    }

    public void send(){
        //TODO There should be call of API methods
    }

    public static class MailBuilder{
        private String heading;
        private List<String> body;
        private Worker worker;

        {
            heading = "Default heading";
            body = new LinkedList<>();
            worker = Worker.phonyWorker();
        }

        public MailBuilder worker(Worker worker){
            this.worker = worker;
            return this;
        }

        public MailBuilder heading(String heading){
            this.heading = heading;
            return this;
        }

        public MailBuilder paragraph(String str){
            body.add(str);
            return this;
        }

        public MailBuilder lineDelimiter(int cnt){
            for(int i = 0; i < cnt; i++) body.add("");
            return this;
        }

        public Mail build(){
            return new Mail(heading, body, worker);
        }
    }
}