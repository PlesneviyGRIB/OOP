public class Main {
    public static void main(String[] args) throws Exception {
        Calculations calculations = new Calculations("+ sqrt - + * / pow 2 5 8 15 7 3 10");
        System.out.println(calculations.getResult());
    }
}