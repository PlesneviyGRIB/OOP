package xml.task1.parsers;

import lombok.Getter;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import xml.task1.Merge;
import xml.task1.MyAttrs;
import xml.task1.My_Utils;
import xml.task1.Person;

import java.util.*;

public class Processing extends DefaultHandler{
    private Map<String, MyAttrs> tagAttr = new HashMap<>();
    @Getter
    private Map<String, Person> people;
    private String currentVal = null;
    public Processing(Map<String, Boolean> availableTag){
        availableTag.forEach((key, value) -> tagAttr.put(key, null));
    }

    @Override
    public void startElement(String uri,
                             String localName,
                             String qName,
                             Attributes attributes) throws SAXException {
        super.startElement(uri, localName, qName, attributes);
        tagAttr.put(qName, MyAttrs.parseAttrs(attributes));
    }

    @Override
    public void endElement(String uri,
                           String localName,
                           String qName) {
        Person.parseTag(qName, currentVal, tagAttr.get(qName));
        tagAttr.put(qName, null);
    }

    @Override
    public void characters(char[] ch,
                           int start,
                           int length){
        String val = (new String(ch, start, length)).trim();
        if(val.length() != 0) currentVal = val;
        else {
            currentVal = null;
        }
    }

    @Override
    public void endDocument() {
        Map<String, Person> people = Person.getPeople();
        Map<String, Person> relativePeople = Person.getRelativeMap();
        List<Person> peopleWithoutId = Person.getPeopleWithoutId();

        relativePeople = Merge.mergeByMyId(relativePeople);
        Merge.mergeRelativeById(people, relativePeople);

        Merge.mergeByIdPeopleWithoutId(people, peopleWithoutId);
        Merge.mergeByMyIdPeopleWithoutId(relativePeople, peopleWithoutId);

        Merge.mergeRelative(people, relativePeople);

        mapSurname(people);
        mapName(people, relativePeople, peopleWithoutId);

//        System.out.println("++++++++++++++++++++++++++++++++++");
//        peopleWithoutId.forEach(System.out::println);
//        System.out.println("++++++++++++++++++++++++++++++++++");

        this.people = new HashMap<>(people);
    }

    private static void mapSurname(Map<String, Person> people){
        List<Person> peop = people.values().stream().filter(person -> person.getSurname() == null && My_Utils.isId(person.getSpouce())).toList();

        peop.forEach(p -> {
            Person spouce = people.get(p.getSpouce());

            if(spouce != null && spouce.getSurname() != null){
                Person person = people.get(p.getId());
                person.setSurname(spouce.getSurname());
            }
        });
    }

    private static boolean myEqual(Set<String> set, Set<String> set0){
        for (String id :set)
            if(My_Utils.isMyId(id) || My_Utils.isId(id) || set0.contains(id))
                return true;
        return false;
    }

    private static Person match(List<Person> set, Person person){
        if(set == null) return null;
        Person tmp = null;

        for (Person p : set) {
            if (Processing.myEqual(person.getChildren(), p.getChildren())) {
                return p;
            }
            if (Processing.myEqual(person.getSiblings(), p.getSiblings())) {
                return p;
            }
            if (person.getParents().equals(p.getParents()))
                return p;
            tmp = p;
        }

        return tmp;
    }

    private static void mapName(Map<String, Person> people, Map<String, Person> relativePeople, List<Person> withoutId){
        Map<String, List<Person>> men = new HashMap<>();
        Map<String, List<Person>> women = new HashMap<>();
        Map<String, List<Person>> withoutGender = new HashMap<>();

        people.forEach((k, v) -> {
            if(v.getSurname() != null)
                if(v.getGender() != null)
                    if(v.getGender().equals("M")) {
                        if(men.containsKey(v.getSurname())){
                            List<Person> tmp = men.get(v.getSurname());
                            tmp.add(v);
                        }else {
                            List<Person> tmp = new ArrayList<>();
                            tmp.add(v);
                            men.put(v.getSurname(), tmp);
                        }
                    }
                    else {
                        if(women.containsKey(v.getSurname())){
                            List<Person> tmp = women.get(v.getSurname());
                            tmp.add(v);
                        }
                        else {
                            List<Person> tmp = new ArrayList<>();
                            tmp.add(v);
                            women.put(v.getSurname(), tmp);
                        }
                    }
                else {
                    if(withoutGender.containsKey(v.getSurname())){
                        List<Person> tmp = withoutGender.get(v.getSurname());
                        tmp.add(v);
                    }
                    else {
                        List<Person> tmp = new ArrayList<>();
                        tmp.add(v);
                        withoutGender.put(v.getSurname(), tmp);
                    }
                }
        });


        withoutId.forEach(p -> {
            if(p.getSurname() != null && p.getName() != null)
                if(p.getGender() != null) {
                    if (p.getGender().equals("M")) {
                        Person person = Processing.match(men.get(p.getSurname()), p);
                        if (person != null)
                            person.setName(p.getName());
                    } else {
                        Person person = Processing.match(women.get(p.getSurname()), p);
                        if (person != null)
                            person.setName(p.getName());
                    }
                }
                else {
                    Person person = Processing.match(withoutGender.get(p.getSurname()), p);
                    if (person != null)
                        person.setName(p.getName());
                }
        });

        relativePeople.forEach((key, p) -> {
            if (p.getSurname() != null && p.getName() != null)
                if (p.getGender() != null) {
                    if (p.getGender().equals("M")) {
                        Person person = Processing.match(men.get(p.getSurname()), p);
                        if (person != null)
                            person.setName(p.getName());
                    } else {
                        Person person = Processing.match(women.get(p.getSurname()), p);
                        if (person != null)
                            person.setName(p.getName());
                    }
                } else {
                    Person person = Processing.match(withoutGender.get(p.getSurname()), p);
                    if (person != null)
                        person.setName(p.getName());
                }
        });
    }
}