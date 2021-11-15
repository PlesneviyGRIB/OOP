import java.util.Stack;

/**
 * words[] - massive of notes(words) is string
 * atom - stack of values for operations in right order
 */
public class Calculations {
    private String[] words;
    private Stack <Double> atom = new Stack<Double>();

    Calculations(String str) throws Exception {
        Double a;
        Double b;

        str = str.trim().replaceAll("\\s{2,}", " ");
        words = str.split(" ");

        for (int i = words.length - 1; i >= 0; i--) {
            if (words[i].matches("-?\\d+(\\.\\d+)?")) {
                atom.push(Double.parseDouble(words[i]));
            } else {
                switch (words[i]) {
                    case "+":
                        atom.push(atom.pop() + atom.pop());
                        break;
                    case "-":
                        atom.push(atom.pop() - atom.pop());
                        break;
                    case "*":
                        atom.push(atom.pop() * atom.pop());
                        break;
                    case "/":
                        a = atom.pop();
                        b = atom.pop();
                        if (b.equals(0.0)) throw new Exception("Division by zero occurred!");

                        atom.push(a / b);
                        break;
                    case "sin":
                        atom.push(Math.sin(atom.pop()));
                        break;
                    case "cos":
                        atom.push(Math.cos(atom.pop()));
                        break;
                    case "sqrt":
                        a = atom.pop();
                        if(a < 0) throw new Exception("Negative value under sqrt!");
                        atom.push(Math.sqrt(a));
                        break;
                    case "pow":
                        atom.push(Math.pow(atom.pop(),atom.pop()));
                        break;
                    case "log":
                        a = atom.pop();
                        if(a <= 0) throw new Exception("Value for function log have to be positive!");
                        atom.push(Math.log(a));
                        break;
                    default:
                        throw new Exception("Wrong term!");
                }
            }
        }
    }

    public Double getResult() {
        return atom.pop();
    }
}