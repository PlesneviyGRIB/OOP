package xml.task1;

import xml.task1.parsers.Preprocessing;
import xml.task1.parsers.Processing;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class XML_Task_1 {

    public static void main(String[] args) throws Exception {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        Preprocessing handler = new Preprocessing();
        SAXParser parser = parserFactory.newSAXParser();
        parser.parse("./resources/people.xml", handler);

        Map<String, Boolean> availableTags = handler.getTags().stream().collect(Collectors.toMap(String::toString, o->false));

        Processing mainHandler = new Processing(availableTags);
        parser.parse("./resources/people.xml", mainHandler);

        Map<String, Person> people = mainHandler.getPeople();


        My_Utils.normalizeData(people);

        //people.values().forEach(System.out::println);

        ValidationReport validationReport = new ValidationReport(people);
        validationReport.report();


        //RESULT
        List<Person> result = new ArrayList<>(people.values());

        result.subList(2000, 2100).forEach(System.out::println);
    }
}