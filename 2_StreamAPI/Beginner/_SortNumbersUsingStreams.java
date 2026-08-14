import java.util.Arrays;
import java.util.List;

public class _SortNumbersUsingStreams {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(42, 15, 8, 23, 16, 4, 37, 10);
        System.out.println("Original list: " + numbers);
        List<Integer> sortedNumbers = numbers.stream()
            .sorted()
            .toList();
        System.out.println("Sorted list: " + sortedNumbers);
    }
}
