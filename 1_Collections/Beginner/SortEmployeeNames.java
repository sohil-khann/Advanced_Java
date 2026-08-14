import java.util.ArrayList;
import java.util.Collections;

public class SortEmployeeNames {
 public static void main(String[] args) {
 ArrayList<String> employees = new ArrayList<>();
 employees.add("Arjun");
 employees.add("Priya");
 employees.add("Rahul");
 employees.add("Anjali");
 employees.add("Vikram");
 System.out.println("Before sorting: " + employees);
 Collections.sort(employees);
 System.out.println("After sorting: " + employees);
 }
}