import java.util.Arrays;
import java.util.List;

public class _ConvertToUppercase {
    public static void main(String[] args) {
        List<String> words = Arrays.asList("java", "stream", "api", "functional", "programming");
        System.out.println("Original list: " + words);
        List<String> uppercaseWords = words.stream()
            .map(String::toUpperCase)
            .toList();
        System.out.println("Uppercase list: " + uppercaseWords);
    }
}
