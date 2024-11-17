import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import org.junit.Test;

public class CalculatorTests {
    @Test
    public void testCalculate() throws Exception {
        Calculator calc = new Calculator();
        assertEquals(25.0, calc.calculate("1.0+48.0 - 12.0 *2.0"), 0.1);
        assertThrows(Exception.class, () -> calc.calculate("1.0+48.0-12.0*2.0/ 0.0"));
        assertEquals(0.0, calc.calculate(""), 0.1);
        assertEquals(7.0, calc.calculate("7"), 0.1);
        assertEquals(-15.0, calc.calculate("15*-1.0"), 0.1);
    }
}