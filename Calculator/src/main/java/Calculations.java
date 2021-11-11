public class Calculations {
    private Double result;
    private Parser parser;

    Calculations (String str) {
        parser = new Parser(str);
    }

    public Double getResult() {
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
                    parser.pushValue(parser.getValue() / parser.getValue());
                    break;
                case "sin":
                    parser.pushValue(Math.sin(parser.getValue()));
                    break;
                case "cos":
                    parser.pushValue(Math.cos(parser.getValue()));
                    break;
                case "sqrt":
                    parser.pushValue(Math.sqrt(parser.getValue()));
                    break;
                case "pow":
                    parser.pushValue(Math.pow(parser.getValue(),parser.getValue()));
                    break;
                case "log":
                    parser.pushValue(Math.log(parser.getValue()));
                    break;
            }
        }
        return parser.getValue();
    }
}