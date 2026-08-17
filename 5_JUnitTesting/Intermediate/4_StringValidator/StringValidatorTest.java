import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringValidatorTest {

    private final StringValidator validator = new StringValidator();

    @ParameterizedTest
    @ValueSource(strings = {"test@example.com", "user.name@domain.co", "admin@test.org"})
    void testValidEmails(String email) {
        assertTrue(validator.isValidEmail(email));
    }

    @ParameterizedTest
    @ValueSource(strings = {"invalid", "missing@dot", "@nodomain.com", ""})
    void testInvalidEmails(String email) {
        assertFalse(validator.isValidEmail(email));
    }

    @Test
    void testNullEmail() {
        assertFalse(validator.isValidEmail(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"1234567890", "9876543210", "0000000000"})
    void testValidPhones(String phone) {
        assertTrue(validator.isValidPhone(phone));
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "12345678901", "abc1234567", ""})
    void testInvalidPhones(String phone) {
        assertFalse(validator.isValidPhone(phone));
    }

    @Test
    void testNullPhone() {
        assertFalse(validator.isValidPhone(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello", "World", "JavaProgramming"})
    void testAlphabeticStrings(String str) {
        assertTrue(validator.isAlphabetic(str));
    }

    @ParameterizedTest
    @ValueSource(strings = {"Hello123", "Test!", "123", "Hello World"})
    void testNonAlphabeticStrings(String str) {
        assertFalse(validator.isAlphabetic(str));
    }

    @Test
    void testNullAlphabetic() {
        assertFalse(validator.isAlphabetic(null));
    }
}
