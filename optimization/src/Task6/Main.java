package Task6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    private int num = 5;

    public static void main(String[] args) {

        Processor processor = new Processor();
        System.out.println(processor.length("123"));

        Main main = new Main();
        processor.caller(main);
        processor.setter(main, 10);

        List<Person> people = main.initPeople();

        main.sort(people);
    }

    private List<Person> initPeople(){
        var res = new ArrayList<Person>();

        res.add(new Person("Egor", 20));
        res.add(new Person("Egor", 40));
        res.add(new Person("Igor", 25));
        res.add(new Person("Egor", 7));
        res.add(new Person("Vyacheslav", 15));

        return res;
    }

    public void sort(List<Person> people){
        for(int i = 0; i < people.size() - 1; i++) {
            var tmp = people.get(i);
            int index = i;

            for (int j = i + 1; j < people.size(); j++) {
                if (tmp.age() > people.get(j).age()) {
                    tmp = people.get(j);
                    index = j;
                }
            }
            var el = people.get(i);
            people.set(i, tmp);
            people.set(index, el);
        }
    }

    public void setNum(int num){
        this.num = num;
    }

    public void method(){
        System.out.println("Method call");
    }
}

class Processor{
    public int length(String str){
        return str.length();
    }

    public void caller(Main main){
        main.method();
    }

    public void setter(Main main, int num){
        main.setNum(num);
    }
}

record Person(String name, int age){}
