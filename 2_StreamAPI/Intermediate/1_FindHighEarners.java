import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindHighEarners {
    static class Employee {
        String name;
        double salary;
        Employee(String name, double salary) {
            this.name = name;
            this.salary = salary;
        }
        public String toString() { return name + " (\u20B9" + salary + ")"; }
    }
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Priya", 60000),
            new Employee("Rahul", 45000),
            new Employee("Arjun", 75000),
            new Employee("Sameer", 52000),
            new Employee("Isha", 48000)
        );
        List<Employee> highEarners = employees.stream()
            .filter(e -> e.salary > 50000)
            .collect(Collectors.toList());
        System.out.println("Employees earning more than \u20B950,000: " + highEarners);
    }
}
