import java.util.ArrayList;
import java.util.HashSet;

public class RemoveDuplicateNumbers {
 public static void main(String[] args) {
 ArrayList<Integer> numbers = new ArrayList<>();
 numbers.add(1);
 numbers.add(2);
 numbers.add(2);
 numbers.add(3);
 numbers.add(4);
 numbers.add(4);
 numbers.add(5);
 System.out.println(\Original List: \ + numbers);
 HashSet<Integer> uniqueNumbers = new HashSet<>(numbers);
 numbers.clear();
 numbers.addAll(uniqueNumbers);
 System.out.println(\List after removing duplicates: \ + numbers);
 }
}