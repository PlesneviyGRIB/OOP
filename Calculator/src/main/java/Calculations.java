/**
 * result - contain result of calculations if newTerm was used; else contain 0
 * parser - class appear for each new term
 * isTerm - true if new term was set; false else
 */
public class Calculations {
    private Double result;
    private Parser parser;
    private boolean isTerm;

    Calculations () {
        isTerm = false;
        result = 0.0;
    }

    /**
     * produce new class parser for each term
     */
    public void newTerm( String str) throws Exception{
        parser = new Parser(str);
        isTerm = true;
    }

    /**
     * calculate term while parser.getOperations > 0
     */
    private void culculateResult() throws Exception{
        Double a;
        Double b;
        String op;
        while(parser.getCntOperations() != 0) {
            op = parser.getOperations();

            switch (op) {
                case "+":
                    parser.pushValue(parser.getValue() + parser.getValue());
                    break;
                case "-":
                    parser.pushValue(parser.getValue() - parser.getValue());
                    break;
                case "*":
                    parser.pushValue(parser.getValue() * parser.getValue());
                    break;
                case "/":
                    a = parser.getValue();
                    b = parser.getValue();
                    if (b.equals(0.0)) throw new Exception("Dvision by zero occurred!");
                    parser.pushValue(a / b);
                    break;
                case "sin":
                    parser.pushValue(Math.sin(parser.getValue()));
                    break;
                case "cos":
                    parser.pushValue(Math.cos(parser.getValue()));
                    break;
                case "sqrt":
                    a = parser.getValue();
                    if(a < 0) throw new Exception("Negative value under sqrt!");
                    parser.pushValue(Math.sqrt(a));
                    break;
                case "pow":
                    parser.pushValue(Math.pow(parser.getValue(),parser.getValue()));
                    break;
                case "log":
                    a = parser.getValue();
                    if(a <= 0) throw new Exception("Value for function log have to be positive!");
                    parser.pushValue(Math.log(a));
                    break;
            }
        }
        result = parser.getValue();
        isTerm = false;
    }

    /**
     * returns result for last term, there wasn't term for calculate return 0.0
     */
    public Double getResult() throws Exception {
        if(isTerm) culculateResult();
        return result;
    }
}