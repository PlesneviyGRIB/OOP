package Task1;

import java.util.ArrayList;
import java.util.List;

public class Task1 {
    public static void main(String[] args) {

        List<Double> list = new ArrayList<>(1000000);

        int repeats = 100;

        Runtime runtime = Runtime.getRuntime();

        while (repeats > 0) {
            for (int i = 0;i < 10000; ++i)
                list.add(1000.0);


            --repeats;

            long usedMemory = runtime.totalMemory() - runtime.freeMemory();
            System.out.println(usedMemory);
        }
    }
}
