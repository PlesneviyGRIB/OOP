import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String str = scanner.nextLine();

        Calculations calculations = new Calculations(str);
        System.out.println(calculations.getResult());
    }
}