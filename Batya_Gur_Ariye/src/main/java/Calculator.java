import java.util.ArrayList;

public class Calculator {
    private ArrayList<Object> exercise;
    public Calculator(){
        
    }
    public Calculator(String input) {
        try {
             exercise = StringConversion.correctString(input);
             System.out.println(calculate(exercise));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public int getFirstMultiplicationOrDivisionIndex(ArrayList<Object> arr) {
        int index = -1;
        for (Object o : arr) {
            index++;
            if (o.equals('*') || o.equals('/'))
                return index;
        }
        return -1;
    }

    public int getFirstAdditionOrSubtractionIndex(ArrayList<Object> arr) {
        int index = -1;
        for (Object o : arr) {
            index++;
            if (o.equals('+') || o.equals('-')){
                return index;
            }
        }
        return -1;
    }

    public double exerciseCalculation(double number1, double number2, char operator) {
        return switch (operator) {
            case '+' -> number1 + number2;
            case '-' -> number1 - number2;
            case '*' -> number1 * number2;
            case '/' -> {
                if (number2 == 0)
                    throw new ArithmeticException("Cannot divide by zero");
                yield number1 / number2;
            }
            default -> throw new IllegalStateException("Unexpected value: " + operator);
        };
    }


    public double calculate(ArrayList<Object> arr) {
        if (arr.isEmpty())
            return 0;
        int multiplicationOrDivision = getFirstMultiplicationOrDivisionIndex(arr);
        int plusOrMinus = getFirstAdditionOrSubtractionIndex(arr);
        if (multiplicationOrDivision == -1 && plusOrMinus == -1)
            return (double) arr.get(0);
        if (multiplicationOrDivision > 0) {
            double sum = exerciseCalculation((double) arr.get(multiplicationOrDivision - 1),
                    (double) arr.get(multiplicationOrDivision + 1),
                    (char) arr.get(multiplicationOrDivision));
            exerciseReduction(arr, multiplicationOrDivision, sum);
        } else if (plusOrMinus > 0) {
            double sum = exerciseCalculation((double) arr.get(plusOrMinus - 1),
                    (double) arr.get(plusOrMinus + 1),
                    (char) arr.get(plusOrMinus));
            arr = exerciseReduction(arr, plusOrMinus, sum);
        }
        return calculate(arr);
    }

    public ArrayList<Object> exerciseReduction(ArrayList<Object> arr, int middleIndex, double sum) {
        arr.set(middleIndex - 1, sum);
        arr.remove(middleIndex + 1);
        arr.remove(middleIndex);
        return arr;
    }


}
