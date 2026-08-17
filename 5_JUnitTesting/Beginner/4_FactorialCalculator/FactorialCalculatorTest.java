import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FactorialCalculatorTest {

    private final FactorialCalculator calculator = new FactorialCalculator();

    @Test
    void testFactorialZero() {
        assertEquals(1, calculator.factorialRecursive(0));
        assertEquals(1, calculator.factorialIterative(0));
    }

    @Test
    void testFactorialOne() {
        assertEquals(1, calculator.factorialRecursive(1));
        assertEquals(1, calculator.factorialIterative(1));
    }

    @Test
    void testFactorialFive() {
        assertEquals(120, calculator.factorialRecursive(5));
        assertEquals(120, calculator.factorialIterative(5));
    }

    @Test
    void testFactorialTen() {
        assertEquals(3628800, calculator.factorialRecursive(10));
        assertEquals(3628800, calculator.factorialIterative(10));
    }

    @Test
    void testFactorialNegativeRecursive() {
        assertThrows(IllegalArgumentException.class, () -> calculator.factorialRecursive(-1));
    }

    @Test
    void testFactorialNegativeIterative() {
        assertThrows(IllegalArgumentException.class, () -> calculator.factorialIterative(-5));
    }

    @Test
    void testBothMethodsProduceSameResult() {
        for (int i = 0; i <= 10; i++) {
            assertEquals(calculator.factorialRecursive(i), calculator.factorialIterative(i));
        }
    }
}
