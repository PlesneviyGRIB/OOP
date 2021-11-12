public class Main {
    public static void main(String[] args) throws Exception {
        Calculations calculations = new Calculations();
        calculations.newTerm("pow 2 8");

        System.out.println(calculations.getResult());
        System.out.println(calculations.getResult());
    }
}