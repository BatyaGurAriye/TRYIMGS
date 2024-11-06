import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

public class StringConversionTests {
    @Test
    public void testDeleteSpaces() {
        assertEquals("1*56+80", StringConversion.deleteSpaces("  1  *5 6+ 80 "));
        assertEquals("test", StringConversion.deleteSpaces("  t est "));
    }

    @Test
    public void testContainsOnlyNumbersAndOperators() throws Exception {
        assertTrue(StringConversion.containsOnlyNumbersAndOperators("1/5.5+99"));
        assertThrows(Exception.class, () -> StringConversion.containsOnlyNumbersAndOperators("1b/55+99a"));
    }

    @Test
    public void testLogicalExerciseStructure() throws Exception {
        assertTrue(StringConversion.logicalExerciseStructure("-1/-55+99"));
        assertTrue(StringConversion.logicalExerciseStructure("1/-5.5+9.9"));
        assertTrue(StringConversion.logicalExerciseStructure("1/-5.5+99"));
        assertThrows(Exception.class, () -> StringConversion.logicalExerciseStructure("1/+55+99"));
        assertThrows(Exception.class, () -> StringConversion.logicalExerciseStructure("1/55.+99"));
        assertThrows(Exception.class, () -> StringConversion.logicalExerciseStructure("1/55+99+1-"));
    }

    @Test
    public void testStringCutting() {
        assertEquals(Arrays.asList(1.0, '+', 48.0, '/', 12.0), (StringConversion.stringCutting("1+48/12")));
        assertEquals(Arrays.asList(1.0, '-', 48.0, '/', 12.0), (StringConversion.stringCutting("1-+48/12")));
        assertEquals(Arrays.asList(1.0, '+', 48.5, '/', 12.0), (StringConversion.stringCutting(".1+48.5/12.")));
        assertEquals(Arrays.asList(1.0, '+', 48.5, '/', -12.0), (StringConversion.stringCutting("1.+48.5/-12.")));
        assertEquals(Arrays.asList(-1.0, '+', 48.5, '/', 12.0), (StringConversion.stringCutting("-1+48.5/12.")));
    }

    @Test
    public void testCorrectString() throws Exception {
        ArrayList<Object> expected = new ArrayList<>(Arrays.asList(123.0, '+', 456.0, '*', 78.0));
        assertEquals(expected, StringConversion.correctString("123 + 456 * 78"));
    }

}