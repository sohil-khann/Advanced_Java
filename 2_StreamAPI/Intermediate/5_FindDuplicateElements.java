import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FindDuplicateElements {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 2, 5, 6, 3, 7, 8, 1, 9, 4);
        Map<Integer, Long> frequency = numbers.stream()
            .collect(Collectors.groupingBy(n -> n, Collectors.counting()));
        List<Integer> duplicates = frequency.entrySet().stream()
            .filter(entry -> entry.getValue() > 1)
            .map(Map.Entry::getKey)
            .toList();
        System.out.println("Original list: " + numbers);
        System.out.println("Duplicate elements: " + duplicates);
    }
}
