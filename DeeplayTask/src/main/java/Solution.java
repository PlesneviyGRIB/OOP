import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class Solution {
    private static final int SIZE = 4;
    private static Map<String, Integer> cost = new HashMap<>();

    static {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream("src/main/resources/my.properties"));
            properties.keySet().forEach(key -> cost.put((String) key, Integer.parseInt((String) properties.get(key))));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static int getResult(String field, String creature) {
        int[][] intField = getIntField(field, creature);
        intField[0][0] = 0;

        int tmp;
        for (int i = 0; i < SIZE; i++)
            for (int k = 0; k < SIZE; k++) {
                tmp = Integer.MAX_VALUE;
                if (k > 0) tmp = intField[i][k - 1];
                if (i > 0 && tmp > intField[i - 1][k]) tmp = intField[i - 1][k];
                if (tmp != Integer.MAX_VALUE) intField[i][k] += tmp;
            }

        return intField[SIZE-1][SIZE-1];
    }

    private static int[][] getIntField(String field, String creature){
        List<Integer> values = new LinkedList<>();
        List.of(field.split("")).forEach(string -> values.add(cost.get(creature + string)));

        int[][] intField = new int[SIZE][SIZE];
        try {
            for (int i = 0; i < SIZE*SIZE; i++) intField[i / SIZE][i % SIZE] = values.get(i);
        } catch (RuntimeException e){
            throw new RuntimeException("Wrong input data");
        }

        return intField;
    }
}