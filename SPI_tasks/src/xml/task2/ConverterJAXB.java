package xml.task2;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import xml.People;
import xml.task1.My_Utils;
import xml.task1.Person;
import xml.task1.parsers.Preprocessing;
import xml.task1.parsers.Processing;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConverterJAXB {
    public static List<Person> task1Starter() throws Exception {
        SAXParserFactory parserFactory = SAXParserFactory.newInstance();
        Preprocessing handler = new Preprocessing();
        SAXParser parser = parserFactory.newSAXParser();
        parser.parse("./resources/people.xml", handler);
        Map<String, Boolean> availableTags = handler.getTags().stream().collect(Collectors.toMap(String::toString, o->false));
        Processing mainHandler = new Processing(availableTags);
        parser.parse("./resources/people.xml", mainHandler);
        Map<String, Person> people = mainHandler.getPeople();
        My_Utils.normalizeData(people);

        return  new ArrayList<>(people.values());
    }

    public static void main(String... args) throws Exception {
        //DATA from Task 1
        List<Person> peopleList = task1Starter();

        People people = new People();
        people.setPeople(peopleList);

        JAXBContext context = JAXBContext.newInstance(People.class);
        Marshaller mar= context.createMarshaller();

        SchemaFactory schemaFactory =
                SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);

        File schemaFile = new File ("./resources/schema.xsd");
        mar.setSchema(schemaFactory.newSchema(schemaFile));

        mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        mar.marshal(people, new File("./resources/mergedPeople.xml"));
    }
}
