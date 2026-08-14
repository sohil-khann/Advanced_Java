import java.util.Arrays;
import java.util.List;

public class _CountNamesStartingWithChar {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Arjun", "Priya", "Rahul", "Sameer", "Isha", "Karan", "Meera");
        char targetChar = 'A';
        long count = names.stream()
            .filter(name -> name.startsWith(String.valueOf(targetChar)))
            .count();
        System.out.println("Names starting with '" + targetChar + "': " + count);
    }
}
