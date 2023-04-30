package xml.task1;

import java.util.*;

public class Merge {
    public static Person merge(Person mainPerson, Person supportivePerson){
        if(supportivePerson.getName() != null) mainPerson.setName(supportivePerson.getName());
        if(supportivePerson.getSurname() != null) mainPerson.setSurname(supportivePerson.getSurname());
        if(supportivePerson.getGender() != null) mainPerson.setGender(supportivePerson.getGender());
        if(supportivePerson.getSpouse() != null) mainPerson.setSpouse(supportivePerson.getSpouse());

        mainPerson.setChildren(My_Utils.addSet(mainPerson.getChildren(), supportivePerson.getChildren()));
        mainPerson.setParents(My_Utils.addSet(mainPerson.getParents(), supportivePerson.getParents()));
        mainPerson.setSiblings(My_Utils.addSet(mainPerson.getSiblings(), supportivePerson.getSiblings()));

        return mainPerson;
    }

    public static Map<String, Person> mergeByMyId(Map<String, Person> relativePeople){

        Map<String, Person> newMap = new HashMap<>(relativePeople);

        relativePeople.forEach((k,v) -> {
            if(My_Utils.isMyId(v.getSpouse())){
                Person person = newMap.get(v.getSpouse());
                person.setSpouse(k);
                if(person.getGender() != null)
                    v.setGender(My_Utils.reverseGender(person.getGender()));
                else
                    if (v.getGender() != null)
                        person.setGender(My_Utils.reverseGender(v.getGender()));

            }

            if(v.getChildren() != null) {
                Set<String> tmp = new HashSet<>(v.getChildren());
                for (String myid : tmp)
                    if (My_Utils.isMyId(myid)) {
                        Person child = newMap.get(myid);
                        Set<String> parents = child.getParents();
                        parents.add(k);
                    }
            }

            if(v.getSiblings() != null) {
                Set<String> tmp = new HashSet<>(v.getSiblings());
                for (String myid : tmp)
                    if (My_Utils.isMyId(myid)) {
                        Person sib = newMap.get(myid);
                        Set<String> siblings = sib.getSiblings();
                        siblings.add(k);
                        siblings.addAll(tmp);
                    }
            }

            if(v.getParents() != null) {
                Set<String> tmp = new HashSet<>(v.getParents());
                for (String myid : tmp)
                    if (My_Utils.isMyId(myid)) {
                        Person parent = newMap.get(myid);
                        Set<String> children = parent.getChildren();
                        children.add(k);
                    }
            }
        });

        return newMap;
    }

    public static void mergeByIdPeopleWithoutId(Map<String, Person> people, List<Person> withoutId){

        withoutId.forEach(p -> {
            String fio = null;

            if(p.getName() != null && p.getSurname() != null)  fio = p.getName() + " " + p.getSurname();

            if(My_Utils.isId(p.getSpouse()) && people.containsKey(p.getSpouse()) && fio != null){
                Person person = people.get(p.getSpouse());
                person.setSpouse(fio);

                if(person.getGender() != null)
                    p.setGender(My_Utils.reverseGender(person.getGender()));
                else
                if (p.getGender() != null)
                    person.setGender(My_Utils.reverseGender(p.getGender()));
            }

            if(p.getChildren() != null && fio != null) {
                Set<String> tmp = new HashSet<>(p.getChildren());
                for (String myId: tmp)
                    if (My_Utils.isId(myId) && people.containsKey(myId)) {
                        Person child = people.get(myId);
                        Set<String> parents = child.getParents();
                        parents.add(fio);
                    }
            }

            if(p.getSiblings() != null && fio != null) {
                Set<String> tmp = new HashSet<>(p.getSiblings());
                for (String myId: p.getSiblings())
                    if (My_Utils.isId(myId) && people.containsKey(myId)) {
                        Person sib = people.get(myId);
                        Set<String> siblings = sib.getSiblings();
                        tmp.addAll(siblings);
                        siblings.add(fio);
                        siblings.addAll(tmp);
                    }
            }

            if(p.getParents() != null && fio != null) {
                Set<String> tmp = new HashSet<>(p.getParents());
                for (String myId : tmp)
                    if (My_Utils.isId(myId) && people.containsKey(myId)) {
                        Person parent = people.get(myId);
                        Set<String> children = parent.getChildren();
                        children.add(fio);
                    }
            }
        });
    }

    public static void mergeByMyIdPeopleWithoutId(Map<String, Person> relativePeople, List<Person> withoitId){

        withoitId.forEach(p -> {
            if(My_Utils.isMyId(p.getSpouse()) && p.getSurname() != null && p.getName() != null){
                    Person person = relativePeople.get(p.getSpouse());
                    person.setSpouse(p.getName() + " " + p.getSurname());

                if(person.getGender() != null)
                    p.setGender(My_Utils.reverseGender(person.getGender()));
                else
                if (p.getGender() != null)
                    person.setGender(My_Utils.reverseGender(p.getGender()));
            }

            if(p.getChildren() != null) {
                Set<String> tmp = new HashSet<>(p.getChildren());
                for (String myid : tmp)
                    if (My_Utils.isMyId(myid) && p.getSurname() != null && p.getName() != null) {
                        Person child = relativePeople.get(myid);
                        Set<String> parents = child.getParents();
                        parents.add(p.getName() + " " + p.getSurname());
                    }
            }

            if(p.getSiblings() != null) {
                Set<String> tmp = new HashSet<>(p.getSiblings());
                for (String myid : tmp)
                    if (My_Utils.isMyId(myid) && p.getSurname() != null && p.getName() != null) {
                        Person sib = relativePeople.get(myid);
                        Set<String> siblings = sib.getSiblings();
                        siblings.add(p.getName() + " " + p.getSurname());
                        siblings.addAll(tmp);
                    }
            }

            if(p.getParents() != null) {
                Set<String> tmp = new HashSet<>(p.getParents());
                for (String myid : tmp)
                    if (My_Utils.isMyId(myid) && p.getSurname() != null && p.getName() != null) {
                        Person parent = relativePeople.get(myid);
                        Set<String> children = parent.getChildren();
                        children.add(p.getName() + " " + p.getSurname());
                    }
            }
        });
    }

    public static void mergeRelative(Map<String, Person> people, Map<String, Person> relativePeople){
        people.forEach((k, p) ->{
            if(My_Utils.isMyId(p.getSpouse())){
                Person person = relativePeople.get(p.getSpouse());
                if(person.getId() != null)
                    p.setSpouse(person.getId());

                if(person.getGender() != null)
                    p.setGender(My_Utils.reverseGender(person.getGender()));
                else
                if (p.getGender() != null)
                    person.setGender(My_Utils.reverseGender(p.getGender()));
            }

            p.setChildren(getIds(p.getChildren(), relativePeople));
            p.setParents(getIds(p.getParents(), relativePeople));
            p.setSiblings(getIds(p.getSiblings(), relativePeople));
        });
    }

    public static void mergeRelativeById(Map<String, Person> people, Map<String, Person> relativePeople){
        relativePeople.entrySet().stream().forEach(entry ->{
            Person person = entry.getValue();
            if(person.getId() != null){
                String id = person.getId();
                people.put(id, merge(people.get(id), person));
            }
        });
    }

    private static Set<String> getIds(Set<String> set, Map<String, Person> relativePeople){
        Set<String> result = new HashSet<>();

        set.forEach(e -> {
            if(My_Utils.isMyId(e)){
                String id = relativePeople.get(e).getId();
                if(id != null)
                    result.add(id);
            }
            else result.add(e);
        });

        return result;
    }
}