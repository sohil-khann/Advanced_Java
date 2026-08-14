import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CalculateAverageSalaryByDept {
    static class Employee {
        String name;
        String department;
        double salary;
        Employee(String name, String department, double salary) {
            this.name = name;
            this.department = department;
            this.salary = salary;
        }
        public String toString() { return name + " (\u20B9" + salary + ")"; }
    }
    public static void main(String[] args) {
        List<Employee> employees = Arrays.asList(
            new Employee("Priya", "Engineering", 120000),
            new Employee("Rahul", "HR", 60000),
            new Employee("Arjun", "Engineering", 140000),
            new Employee("Sameer", "Sales", 80000),
            new Employee("Isha", "HR", 65000),
            new Employee("Karan", "Sales", 90000)
        );
        Map<String, Double> avgSalaryByDept = employees.stream()
            .collect(Collectors.groupingBy(
                e -> e.department,
                Collectors.averagingDouble(e -> e.salary)
            ));
        avgSalaryByDept.forEach((dept, avg) ->
            System.out.println(dept + " average salary: \u20B9" + String.format("%.2f", avg))
        );
    }
}
