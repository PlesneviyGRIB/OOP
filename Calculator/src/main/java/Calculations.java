import java.util.Stack;

/**
 * words[] - massive of notes(words) is string
 * atom - stack of values for operations in right order
 */
public class Calculations {
    private String[] words;
    private Stack <Double> atom = new Stack<Double>();

    public Double newCalculation (String str) throws Exception {
        str = str.trim().replaceAll("\\s{2,}", " ");
        words = str.split(" ");

        for (int i = words.length - 1; i >= 0; i--) {
            if (words[i].matches("-?\\d+(\\.\\d+)?")) {
                atom.push(Double.parseDouble(words[i]));
            } else {
                Sw(words[i]);
            }
        }
        return getResult();
    }

    private void Sw(String words) throws Exception{
        Double a;
        Double b;

        switch (words) {
            case "+":
                atom.push(p() + p());
                break;
            case "-":
                atom.push(p() - p());
                break;
            case "*":
                atom.push(p() * p());
                break;
            case "/":
                a = p();
                b = p();
                if (b.equals(0.0)) throw new Exception("Division by zero occurred!");

                atom.push(a / b);
                break;
            case "sin":
                atom.push(Math.sin(p()));
                break;
            case "cos":
                atom.push(Math.cos(p()));
                break;
            case "sqrt":
                a = p();
                if(a < 0) throw new Exception("Negative value under sqrt!");
                atom.push(Math.sqrt(a));
                break;
            case "pow":
                atom.push(Math.pow(p(),p()));
                break;
            case "log":
                a = p();
                if(a <= 0) throw new Exception("Value for function log have to be positive!");
                atom.push(Math.log(a));
                break;
            default:
                throw new Exception("Wrong term!");
        }
    }

    private Double p() throws Exception{
        if(atom.empty()) throw new Exception("Wrong term!");
        return atom.pop();
    }

    private Double getResult() throws Exception {
        Double res = atom.pop();
        if(!atom.empty()) throw new Exception("Wrong term!");
        return res;
    }
}