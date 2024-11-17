import java.util.ArrayList;

public class Evaluator {
    public double calculationAnExerciseArray(ArrayList<Token> tokens) throws Exception {
        if (tokens.isEmpty()) {
            return 0;
        }
        if (tokens.size() == 1) {
            return ((NumberToken) tokens.get(0)).getValue();
        }
        int operatorIndex = getNextOperator(tokens);
        double result = calculationAroundTheSelectedOperator(tokens, operatorIndex);
        exerciseReductionWithResult(tokens, operatorIndex, result);
        return calculationAnExerciseArray(tokens);
    }

    public int getNextOperator(ArrayList<Token> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            if ((tokens.get(i) instanceof OperatorToken operator)
                    && (operator.getValue() == '*' || operator.getValue() == '/')) {
                return i;
            }
        }
        for (int i = 0; i < tokens.size(); i++) {
            if ((tokens.get(i) instanceof OperatorToken operator)
                    && (operator.getValue() == '+' || operator.getValue() == '-')) {
                return i;
            }
        }
        return -1;
    }

    private double calculationAroundTheSelectedOperator(ArrayList<Token> tokens, int operatorIndex) throws Exception {
        OperatorToken operatorToken = (OperatorToken) tokens.get(operatorIndex);
        double left = doubleConvert((NumberToken) tokens.get(operatorIndex - 1));
        double right = doubleConvert((NumberToken) tokens.get(operatorIndex + 1));
        return operatorToken.apply(left, right);
    }

    private void exerciseReductionWithResult(ArrayList<Token> tokens, int index, double sum) throws Exception {
        tokens.set(index - 1, new NumberToken(sum));
        tokens.remove(index + 1);
        tokens.remove(index);
    }

    private double doubleConvert(NumberToken numberToken) {
        double doubleNumber = numberToken.getValue();
        return doubleNumber;
    }
}
