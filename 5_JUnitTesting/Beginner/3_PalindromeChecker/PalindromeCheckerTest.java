import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PalindromeCheckerTest {

    @Test
    void testRacecar() {
        assertTrue(PalindromeChecker.isPalindrome("racecar"));
    }

    @Test
    void testMadam() {
        assertTrue(PalindromeChecker.isPalindrome("madam"));
    }

    @Test
    void testHello() {
        assertFalse(PalindromeChecker.isPalindrome("hello"));
    }

    @Test
    void testNull() {
        assertFalse(PalindromeChecker.isPalindrome(null));
    }

    @Test
    void testEmptyString() {
        assertTrue(PalindromeChecker.isPalindrome(""));
    }

    @Test
    void testWithSpaces() {
        assertTrue(PalindromeChecker.isPalindrome("A man a plan a canal Panama"));
    }

    @Test
    void testCaseSensitive() {
        assertTrue(PalindromeChecker.isPalindrome("Racecar"));
    }
}
