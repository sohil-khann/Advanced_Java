import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TemperatureConverterTest {

    private final TemperatureConverter converter = new TemperatureConverter();

    @Test
    void testCelsiusToFahrenheitZero() {
        assertEquals(32.0, converter.celsiusToFahrenheit(0), 0.0001);
    }

    @Test
    void testCelsiusToFahrenheitHundred() {
        assertEquals(212.0, converter.celsiusToFahrenheit(100), 0.0001);
    }

    @Test
    void testCelsiusToFahrenheitMinusForty() {
        assertEquals(-40.0, converter.celsiusToFahrenheit(-40), 0.0001);
    }

    @Test
    void testFreezingPoint() {
        assertEquals(0.0, converter.fahrenheitToCelsius(32), 0.0001);
    }

    @Test
    void testFahrenheitToCelsiusRoundTrip() {
        double original = 25.0;
        assertEquals(original, converter.celsiusToFahrenheit(converter.fahrenheitToCelsius(original)), 0.0001);
    }

    @Test
    void testCelsiusToFahrenheitBoilingPoint() {
        assertEquals(212.0, converter.celsiusToFahrenheit(100), 0.0001);
    }
}
