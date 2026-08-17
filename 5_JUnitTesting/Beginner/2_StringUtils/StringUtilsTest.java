import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {

    private final StringUtils stringUtils = new StringUtils();

    @Test
    void testIsPalindrome() {
        assertTrue(stringUtils.isPalindrome("madam"));
        assertTrue(stringUtils.isPalindrome("racecar"));
        assertTrue(stringUtils.isPalindrome("A man a plan a canal Panama".replaceAll("\\s+", "")));
        assertFalse(stringUtils.isPalindrome("hello"));
        assertFalse(stringUtils.isPalindrome("java"));
    }

    @Test
    void testIsPalindromeWithNull() {
        assertFalse(stringUtils.isPalindrome(null));
    }

    @Test
    void testReverse() {
        assertEquals("olleh", stringUtils.reverse("hello"));
        assertEquals("madam", stringUtils.reverse("madam"));
        assertEquals("", stringUtils.reverse(""));
        assertNull(stringUtils.reverse(null));
    }

    @Test
    void testToTitleCase() {
        assertEquals("Hello World", stringUtils.toTitleCase("hello world"));
        assertEquals("Java Programming", stringUtils.toTitleCase("JAVA PROGRAMMING"));
        assertEquals("A", stringUtils.toTitleCase("a"));
        assertNull(stringUtils.toTitleCase(null));
        assertEquals("", stringUtils.toTitleCase(""));
    }

    @Test
    void testToTitleCaseMultipleSpaces() {
        assertEquals("Hello   World", stringUtils.toTitleCase("hello   world"));
    }
}
