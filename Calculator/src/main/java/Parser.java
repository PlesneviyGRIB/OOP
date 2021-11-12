import java.util.Stack;

/**
 * words[] - massive of notes(words) is string
 * operations - stack of operations in right order
 * value - stack of values for operations in right order
 */
public class Parser {
    private String[] words;
    private Stack <String> operations = new Stack<String>();
    private Stack <Double> value = new Stack<Double>();
    private int cntOperations;

    Parser(String str) throws Exception {
        str = str.trim().replaceAll("\\s{2,}", " ");
        words = str.split(" ");

        try {
            for (int i = words.length - 1; i >= (words.length - words.length / 2 - words.length % 2); i--) {
                value.push(Double.parseDouble(words[i]));
            }
            for (int i = 0; i < words.length / 2; i++) {
                operations.push(words[i]);
            }
            cntOperations = words.length / 2;
        }
        catch (Exception e) {
            throw new Exception("Wrong term!");
        }
    }

    public void pushValue(Double _value) {
        value.push(_value);
    }

    public String getOperations() throws Exception {
        if(operations.empty()) throw new Exception("Wrong term!");
        cntOperations--;
        return operations.pop();
    }

    public Double getValue() throws Exception {
        if(value.empty()) throw new Exception("Wrong term!");
        return value.pop();
    }

    public int getCntOperations() {
        return cntOperations;
    }
}