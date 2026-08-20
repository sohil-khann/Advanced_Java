import java.util.*;
import java.util.stream.*;

public class StreamApi {

    // 1. Sum of Array Elements
    static void sumExample() {
        int[] arr = {30, 20, 10};
        int sum = Arrays.stream(arr).sum();
        System.out.println("Sum: " + sum);
    }

    // 2. Maximum Value
    static void maxExample() {
        int[] arr = {30, 20, 10};
        int max = Arrays.stream(arr).max().getAsInt();
        System.out.println("Max: " + max);
    }

    // 3. Average
    static void averageExample() {
        int[] arr = {30, 20, 10};
        double avg = Arrays.stream(arr).average().getAsDouble();
        System.out.println("Average: " + avg);
    }

    // 4. Filtering
    static void filterExample() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie", "David");
        List<String> longNames = names.stream()
                .filter(name -> name.length() > 4)
                .collect(Collectors.toList());
        System.out.println("Names longer than 4 chars: " + longNames);
    }

    // 5. Mapping
    static void mapExample() {
        List<String> names = Arrays.asList("Alice", "Bob", "Charlie");
        List<Integer> nameLengths = names.stream()
                .map(String::length)
                .collect(Collectors.toList());
        System.out.println("Name lengths: " + nameLengths);
    }

    // 6. Sorting
    static void sortExample() {
        List<Integer> numbers = Arrays.asList(5, 2, 8, 1, 3);
        List<Integer> sorted = numbers.stream()
                .sorted()
                .collect(Collectors.toList());
        System.out.println("Sorted numbers: " + sorted);
    }

    // 7. Infinite Stream with Filter
    static void infiniteStreamExample() {
        System.out.println("First 10 numbers containing '5':");
        Stream.iterate(1, x -> x + 1)
                .filter(x -> x.toString().contains("5"))
                .limit(10)
                .forEach(System.out::println);
    }

    // 8. Collecting to Map
    static void toMapExample() {
        List<String> words = Arrays.asList("apple", "banana", "cherry");
        Map<String, Integer> wordLengthMap = words.stream()
                .collect(Collectors.toMap(w -> w, String::length));
        System.out.println("Word length map: " + wordLengthMap);
    }

    public static void main(String[] args) {
        sumExample();
        maxExample();
        averageExample();
        filterExample();
        mapExample();
        sortExample();
        infiniteStreamExample();
        toMapExample();
    }
}
