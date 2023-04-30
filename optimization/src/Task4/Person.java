package Task4;

public class Person {
    public final String name;
    public int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return String.format("name: %s, age: %d", name, age);
    }
}
