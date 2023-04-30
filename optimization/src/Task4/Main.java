package Task4;

public class Main {
    static {
        System.load("/home/egor/IdeaProjects/OOP/optimization/src/Task4/native/lib.so");
    }

    public static void main(String[] args) {
        memoryOverflow();

        System.out.println(getStringLength("Hello from C"));

        Person person = new Person("Egor", 20);

        printPersonInfo(person);

        changePersonAge(person);

        printPersonInfo(person);

        long ptr = getStructPointer();
        printStructByPointer(ptr);
    }

    public static native void memoryOverflow();

    public static native void error();

    public static native int getStringLength(String str);

    public static native void printPersonInfo(Person person);

    public static native void changePersonAge(Person person);

    public static native long getStructPointer();

    public static native void printStructByPointer(long ptr);
}
