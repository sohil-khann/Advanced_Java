import java.util.Arrays;
import java.util.List;

public class _FindSumUsingStreams {
    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(10, 20, 30, 40, 50);
        int sum = numbers.stream()
            .mapToInt(Integer::intValue)
            .sum();
        System.out.println("List: " + numbers);
        System.out.println("Sum: " + sum);
    }
}
