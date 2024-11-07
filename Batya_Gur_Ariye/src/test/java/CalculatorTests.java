import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;

public class CalculatorTests {
    @Test
    public void testReturnsFirstMultiplicationOrDivisionOperator() {
        Calculator help = new Calculator();
        assertEquals(3, help.getFirstMultiplicationOrDivisionIndex(
                new ArrayList<>(Arrays.asList(1, '+', 48, '/', 12, '*', 2))));
        assertEquals(-1, help.getFirstMultiplicationOrDivisionIndex(
                new ArrayList<>(Arrays.asList(1, '+', 48, '-', 12, '+', 2))));
    }

    @Test
    public void testReturnsFirstPlusOrMinusOperator() {
        Calculator help = new Calculator();
        assertEquals(1, help.getFirstAdditionOrSubtractionIndex(
                new ArrayList<>(Arrays.asList(1, '+', 48, '-', 12, '*', 2))));
        assertEquals(-1, help.getFirstAdditionOrSubtractionIndex(
                new ArrayList<>(Arrays.asList(1, '*', 48, '*', 12, '/', 2))));
    }

    @Test
    public void testExerciseCalculation() {
        Calculator help = new Calculator();
        assertEquals(8.0, help.exerciseCalculation(10.0, 2.0, '-'), 0.1);
        assertEquals(49.0, help.exerciseCalculation(1.0, 48.0, '+'), 0.1);
        assertEquals(96.0, help.exerciseCalculation(2.0, 48.0, '*'), 0.1);
        assertEquals(4.0, help.exerciseCalculation(12.0, 3.0, '/'), 0.1);
        assertThrows(ArithmeticException.class, () -> help.exerciseCalculation(12.0, 0, '/'));
    }

    @Test
    public void testExerciseReduction() {
        Calculator help = new Calculator();
        assertEquals(Arrays.asList(1, '+', 48, '-', 24.0), help.exerciseReduction(
                new ArrayList<>(Arrays.asList(1, '+', 48, '-', 12, '*', 2)), 5, 24));
        assertEquals(List.of(48.0), help.exerciseReduction(
                new ArrayList<>(Arrays.asList(1, '*', 48)), 1, 48));
    }

    @Test
    public void testCalculate() {
        Calculator help = new Calculator();
        assertEquals(25.0, help.calculate(new ArrayList<>(Arrays.asList(1.0, '+', 48.0, '-', 12.0, '*', 2.0))), 0.1);
        assertThrows(ArithmeticException.class, () -> help.calculate(new ArrayList<>(Arrays.asList(1.0, '+', 48.0, '-', 12.0, '*', 2.0, '/', 0.0))));
        assertEquals(0.0, help.calculate(new ArrayList<>()), 0.1);
        assertEquals(7.0, help.calculate(new ArrayList<>(List.of(7.0))), 0.1);
        assertEquals(-15.0, help.calculate(new ArrayList<>(Arrays.asList(15.0, '*', -1.0))), 0.1);
    }

}
