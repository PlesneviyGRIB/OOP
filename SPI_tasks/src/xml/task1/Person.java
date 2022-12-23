package xml.task1;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@Data
@NoArgsConstructor
public class Person implements Comparable{
    private String id;           // id
    private String name;         // fullname first firstname
    private String surname;      // fullname family family-name surname
    private String gender;       // gender
    private Set<String> parents = new HashSet<>();    // parent mother father
    private Set<String> siblings = new HashSet<>();   //siblings brother sister
    private Set<String> children = new HashSet<>();   //children daughter son child
    private String spouce;       //wife spouce husband
    private Map<String, String> validation = new HashMap<>(); //siblings-number children-number
    private static Person currentPerson = new Person();
    private static final Map<String, Person> people = new HashMap<>();
    private static final List<Person> peopleWithoutId = new ArrayList<>();

    private static int cnt = 0;
    private static final Map<String, Person> relativeMap = new HashMap<>();

    private static String setRM(Person person){
        String id = "G" + cnt++;
        relativeMap.put(id, person);
        return id;
    }

    public static void parseTag(String tagName, String value, MyAttrs myAttrs){
        switch (tagName){
            case "id" -> idHandler(myAttrs);
            case "first", "family", "surname", "family-name" -> fullNameHandler(tagName, myAttrs, value);
            case "gender" -> genderHandler(myAttrs, value);
            case "parent", "mother", "father" -> parentsHandler(tagName, myAttrs, value);
            case "siblings", "brother", "sister" -> siblingsHandler(tagName, myAttrs, value);
            case "children", "daughter", "son", "child" -> childrenHandler(tagName, myAttrs, value);
            case "spouce", "wife", "husband" -> spouceHandler(tagName, myAttrs, value);
            case "siblings-number", "children-number" -> validationHandler(tagName, myAttrs);
        }

        if(tagName.equals("person")){
            if(myAttrs.getId() != null)
                currentPerson.setId(myAttrs.getId());
            safePush(currentPerson);
            currentPerson = new Person();
        }
    };

    private static void safePush(Person person){
        if(currentPerson.getId() != null) {
            if(people.containsKey(person.id)){
                Person tmp = people.get(person.id);
                people.put(person.id, Merge.merge(tmp, person));
            }
            else people.put(currentPerson.id, currentPerson);
        }
        else peopleWithoutId.add(currentPerson);
    }

    private static void idHandler(MyAttrs myAttrs){
        if(myAttrs.getValue() != null)
            currentPerson.setId(myAttrs.getValue());
    }

    private static void fullNameHandler(String tag, MyAttrs myAttrs, String value){
        if(tag.equals("surname") || tag.equals("family") || tag.equals("family-name")){
            if(myAttrs.getValue() != null)
                currentPerson.setSurname(myAttrs.getValue());
            if (value != null)
                currentPerson.setSurname(value);
        }

        if (tag.equals("first"))
            if (value != null)
                currentPerson.setName(value);
    }

    private static void genderHandler(MyAttrs myAttrs, String value){
        if(value != null)
            currentPerson.setGender(My_Utils.normalizeGender(value));

        if(myAttrs.getValue() != null)
            currentPerson.setGender(My_Utils.normalizeGender(myAttrs.getValue()));
    }

    private static void setParentById(String id, String gender){
        Set<String> parents = currentPerson.getParents();
        parents.add(id);


        if(people.containsKey(id)){
            Person parent = people.get(id);

            if(gender != null) parent.setGender(gender);

            Set<String> children = parent.getChildren();
            if(currentPerson.getId() != null)
                children.add(currentPerson.getId());
            else children.add(setRM(currentPerson));
        }
        else {
            Person parent = new Person();
            parent.setId(id);

            if(gender != null) parent.setGender(gender);
            Set<String> children = parent.getChildren();

            if(currentPerson.getId() != null)
                children.add(currentPerson.getId());
            else children.add(setRM(currentPerson));

            people.put(parent.id, parent);
        }
    }

    private static void setParentByFullName(String fullname, String gender){
        Set<String> parents = currentPerson.getParents();
        parents.add(fullname);

        Person parent = new Person();
        parent.setName(fullname.split(" ")[0]);
        parent.setSurname(fullname.split(" ")[1]);

        if(gender != null)
            parent.setGender(gender);

        Set<String> children = parent.getChildren();

        if(currentPerson.getId() != null)
            children.add(currentPerson.getId());
        else children.add(setRM(currentPerson));

        peopleWithoutId.add(parent);
    }

    private static void parentsHandler(String tag, MyAttrs myAttrs, String value){
        if(tag.equals("parent"))
            if(myAttrs.getValue() != null && My_Utils.notUnknown(myAttrs.getValue()))
                setParentById(myAttrs.getValue(), null);


        if(tag.equals("mother"))
            if (value != null && My_Utils.notUnknown(value))
                setParentByFullName(value, "F");


        if(tag.equals("father"))
            if (value != null && My_Utils.notUnknown(value))
                setParentByFullName(value, "M");
    }

    private static void setSiblingsByFullName(String fullname, String gender){
        Set<String> siblings = currentPerson.getSiblings();
        siblings.add(fullname);

        Person sib = new Person();
        sib.setName(fullname.split(" ")[0]);
        sib.setSurname(fullname.split(" ")[1]);

        if(gender != null)
            sib.setGender(gender);

        Set<String> siblings1 = sib.getSiblings();

        if(currentPerson.getId() != null)
            siblings1.add(currentPerson.getId());
        else siblings1.add(setRM(currentPerson));

        siblings1.addAll(siblings);
        siblings.addAll(siblings1);

        peopleWithoutId.add(sib);
    }

    private static void setSiblingsById(String idStr){
        String[] ids = idStr.split(" ");

        for(String id: ids) {
            Set<String> siblings = currentPerson.getSiblings();
            siblings.add(id);

            if (people.containsKey(id)) {
                Person sib = people.get(id);

                Set<String> siblings1 = sib.getSiblings();

                if (currentPerson.getId() != null)
                    siblings1.add(currentPerson.getId());
                else siblings1.add(setRM(currentPerson));

                siblings1.addAll(siblings);
                siblings.addAll(siblings1);
            } else {
                Person sib = new Person();
                sib.setId(id);

                Set<String> siblings1 = sib.getSiblings();

                if (currentPerson.getId() != null)
                    siblings1.add(currentPerson.getId());
                else siblings1.add(setRM(currentPerson));

                siblings1.addAll(siblings);
                siblings.addAll(siblings1);

                people.put(sib.id, sib);
            }
        }
    }

    private static void siblingsHandler(String tag, MyAttrs myAttrs, String value){
        if(tag.equals("siblings"))
            if(myAttrs.getVal() != null)
                setSiblingsById(myAttrs.getVal());

        if(tag.equals("brother"))
            setSiblingsByFullName(value, "M");

        if(tag.equals("sister"))
            setSiblingsByFullName(value, "F");
    }

    private static void setChildByFullName(String fullname){
        Set<String> children = currentPerson.getChildren();
        children.add(fullname);

        Person child = new Person();
        child.setName(fullname.split(" ")[0]);
        child.setSurname(fullname.split(" ")[1]);

        Set<String> parents = child.getParents();

        if(currentPerson.getId() != null)
            parents.add(currentPerson.getId());
        else parents.add(setRM(currentPerson));

        peopleWithoutId.add(child);
    }

    private static void setChildById(String id, String gender){
        Set<String> children = currentPerson.getChildren();
        children.add(id);

        if(people.containsKey(id)){
            Person child = people.get(id);

            if(gender != null) child.setGender(gender);

            Set<String> parents = child.getParents();
            if(currentPerson.getId() != null)
                parents.add(currentPerson.getId());
            else parents.add(setRM(currentPerson));
        }
        else {
            Person child = new Person();
            child.setId(id);

            if(gender != null) child.setGender(gender);
            Set<String> parents = child.getParents();

            if(currentPerson.getId() != null)
                parents.add(currentPerson.getId());
            else parents.add(setRM(currentPerson));

            people.put(child.id, child);
        }
    }

    private static void childrenHandler(String tag, MyAttrs myAttrs, String value){

        if(tag.equals("child"))
            setChildByFullName(value);

        if(tag.equals("son"))
            setChildById(myAttrs.getId(), "M");

        if(tag.equals("daughter"))
            setChildById(myAttrs.getId(), "F");
    }

    private static void setSpouceByFullName(String fullname, String gender){
        Person spouce = new Person();
        spouce.setName(fullname.split(" ")[0]);
        spouce.setSurname(fullname.split(" ")[1]);

        if(gender != null) {
            spouce.setGender(gender);
            if(currentPerson.gender == null)
                currentPerson.setGender(My_Utils.reverseGender(gender));
        }

        if(currentPerson.getId() != null)
            spouce.setSpouce(currentPerson.getId());
        else spouce.setSpouce(setRM(currentPerson));

        currentPerson.setSpouce(fullname);

        peopleWithoutId.add(spouce);
    }

    private static void setSpouceById(String id, String gender){
        if(people.containsKey(id)){
            Person spouce = people.get(id);

            if(gender != null) {
                spouce.setGender(gender);
                if(currentPerson.gender == null)
                    currentPerson.setGender(My_Utils.reverseGender(gender));
            }

            if(currentPerson.getId() != null)
                spouce.setSpouce(currentPerson.getId());
            else spouce.setSpouce(setRM(currentPerson));
        }
        else {
            Person spouce = new Person();
            spouce.setId(id);

            if(gender != null) {
                spouce.setGender(gender);
                if(currentPerson.gender == null)
                    currentPerson.setGender(My_Utils.reverseGender(gender));
            }

            if(currentPerson.getId() != null)
                spouce.setSpouce(currentPerson.getId());
            else spouce.setSpouce(setRM(currentPerson));

            people.put(spouce.id, spouce);
        }
        currentPerson.setSpouce(id);
    }

    private static void spouceHandler(String tag, MyAttrs myAttrs, String value){
        if(tag.equals("spouce")){
            if(myAttrs.getValue() != null && !myAttrs.getValue().equals("NONE"))
                setSpouceByFullName(myAttrs.getValue(), null);
        }

        if(tag.equals("husband")){
            if(myAttrs.getValue() != null && !myAttrs.getValue().equals("NONE"))
                setSpouceById(myAttrs.getValue(), "M");
        }

        if(tag.equals("wife")){
            if(myAttrs.getValue() != null && !myAttrs.getValue().equals("NONE"))
                setSpouceById(myAttrs.getValue(), "F");
        }
    }

    private static void validationHandler(String tag, MyAttrs myAttrs) {
        currentPerson.validation.put(tag, myAttrs.getValue());
    }

    public static List<Person> getResultList(){
        List<Person> tmp = new LinkedList<>();
        people.forEach((k,v) -> tmp.add(v));
        return tmp;
    }

    public static Map<String, Person> getRelativeMap(){
        return new HashMap<>(relativeMap);
    }
    public static Map<String, Person> getPeople(){
        return new HashMap<>(people);
    }

    public static List<Person> getPeopleWithoutId(){
        return new ArrayList<>(peopleWithoutId);
    }

    @Override
    public int compareTo(Object o) {
        if(o.getClass() != Person.class) return 0;
        Person person = (Person) o;
        return this.id.compareTo(person.id);
    }
}
