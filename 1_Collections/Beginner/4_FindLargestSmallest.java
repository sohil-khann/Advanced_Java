import java.util.ArrayList;

public class FindLargestSmallest {
 public static void main(String[] args) {
 ArrayList<Integer> numbers = new ArrayList<>();
 numbers.add(45);
 numbers.add(12);
 numbers.add(78);
 numbers.add(23);
 numbers.add(56);
 numbers.add(89);
 numbers.add(34);
 if (numbers.isEmpty()) {
 System.out.println(\List is empty\);
 return;
 }
 int largest = numbers.get(0);
 int smallest = numbers.get(0);
 for (int num : numbers) {
 if (num > largest) { largest = num; }
 if (num < smallest) { smallest = num; }
 }
 System.out.println(\Largest number: \ + largest);
 System.out.println(\Smallest number: \ + smallest);
 }
}