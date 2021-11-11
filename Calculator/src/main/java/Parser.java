import java.util.Stack;

public class Parser {
    private String[] words;
    private Stack <String> operations = new Stack<String>();
    private Stack <Double> value = new Stack<Double>();
    private int cntOperations;

    Parser(String str) {
        words = str.split(" ");

        for (int i = words.length - 1; i >= (words.length - words.length / 2 - words.length % 2); i--) {
            value.push(Double.parseDouble(words[i]));
        }
        for (int i = 0; i < words.length / 2; i++) {
            operations.push(words[i]);
        }
        cntOperations = words.length / 2;
    }

    public void pushValue(Double _value) {
        value.push(_value);
    }

    public String getOperations() {
        cntOperations--;
        return operations.pop();
    }

    public Double getValue() {
        return value.pop();
    }

    public int getCntOperations() {
        return cntOperations;
    }
}