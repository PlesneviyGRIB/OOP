package xml.task1;
import org.xml.sax.Attributes;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class My_Utils {
    public static String checkIdAttr(Attributes attributes){
        String id = attributes.getValue("id");
        return id;
    }

    public static Set<String> addSet(Set<String> set, Set<String> set1){
        set.addAll(set1);
        return set;
    }

    public static String normalizeGender(String g){
        if(g.equals("male")) return "M";
        if(g.equals("female")) return "F";
        return g;
    }

    public static boolean notUnknown(String s){
        return !"UNKNOWN".equals(s);
    }

    public static String reverseGender(String gender){
        if(gender.equals("M")) return "F";
        if(gender.equals("F")) return "M";
        return null;
    }

    public static boolean isMyId(String str){
        if(str == null) return false;
        return str.matches("G[0-9]{1,}");
    }

    public static boolean isId(String str){
        if(str == null) return false;
        return str.matches("P[0-9]{1,}");
    }

    public static void normalizeData(Map<String, Person> people){
        Map<String, Person> peop = new HashMap<>(people);

        peop.values().forEach(p -> {
            Person person = people.get(p.getId());
            Set<String> siblings = person.getSiblings();
            siblings.remove(person.getId());

            person.setParents(My_Utils.idToFullName(person.getParents(), people));
            person.setChildren(My_Utils.idToFullName(person.getChildren(), people));
            person.setSiblings(My_Utils.idToFullName(person.getSiblings(), people));

            if(My_Utils.isId(person.getSpouce())){
                Person person1 = people.get(person.getSpouce());
                if(person1.getName() != null && person1.getSurname() != null){
                    String fio = person.getName() + " " + person.getSurname();
                    person.setSpouce(fio);
                }
            }
        });

        people.entrySet().forEach(entry -> {
            Person person = entry.getValue();
            if(person.getName() == null) person.setName("");
            if(person.getSurname() == null) person.setSurname("");
            if(person.getGender() == null) person.setGender("");
            if(person.getSpouce() == null) person.setSpouce("");
        });
    }

    private static Set<String> idToFullName(Set<String> ids, Map<String, Person> people){
        Set<String> fullNames = new HashSet<>();

        ids.forEach(id ->{
            if(My_Utils.isId(id)){
                Person person = people.get(id);
                if(person.getName() != null && person.getSurname() != null){
                    String fio = person.getName() + " " + person.getSurname();
                    fullNames.add(fio);
                }
                else fullNames.add(id);
            }
            else fullNames.add(id);
        });

        return fullNames;
    }
}
