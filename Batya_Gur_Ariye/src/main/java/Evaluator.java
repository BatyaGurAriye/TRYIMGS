import java.util.ArrayList;

public class Evaluator {
    public double evaluate(ArrayList<Token> tokens) throws Exception {
        if (tokens.isEmpty()) {
            return 0;
        }
        int multiplicationOrDivision = getFirstMultiplicationOrDivisionIndex(tokens);
        int plusOrMinus = getFirstAdditionOrSubtractionIndex(tokens);

        if (multiplicationOrDivision == -1 && plusOrMinus == -1) {
            double result = ((NumberToken) tokens.get(0)).getValue();
            System.out.println(result);
            return (result);
        }


        if (multiplicationOrDivision > 0) {
            double sum = ((OperatorToken) tokens.get(multiplicationOrDivision)).apply(
                    doubleConvert((NumberToken) tokens.get(multiplicationOrDivision - 1)),
                    doubleConvert((NumberToken) tokens.get(multiplicationOrDivision + 1))
            );
            exerciseReduction(tokens, multiplicationOrDivision, sum);
        } else if (plusOrMinus > 0) {
            double sum = ((OperatorToken) tokens.get(plusOrMinus)).apply(
                    doubleConvert((NumberToken) tokens.get(plusOrMinus - 1)),
                    doubleConvert((NumberToken) tokens.get(plusOrMinus + 1))
            );
            exerciseReduction(tokens, plusOrMinus, sum);
        }
        return evaluate(tokens);
    }

    private int getFirstMultiplicationOrDivisionIndex(ArrayList<Token> tokens) {
        int index = -1;
        for (Token o : tokens) {
            index++;
            if (o instanceof OperatorToken ot)
                if (ot.getValue() == '*' || ot.getValue() == '/') {
                    return index;
                }
        }
        return -1;
    }

    private int getFirstAdditionOrSubtractionIndex(ArrayList<Token> tokens) {
        int index = -1;
        for (Token o : tokens) {
            index++;
            if (o instanceof OperatorToken ot &&
                    (ot.getValue() == '+' || ot.getValue() == '-')) {
                return index;
            }
        }
        return -1;
    }

    private void exerciseReduction(ArrayList<Token> tokens, int index, double sum) throws Exception {
        tokens.set(index - 1, new NumberToken(sum));
        tokens.remove(index + 1);
        tokens.remove(index);
    }

    private double doubleConvert(NumberToken numberToken) {
        double doubleNumber = numberToken.getValue();
        return doubleNumber;
    }
}
