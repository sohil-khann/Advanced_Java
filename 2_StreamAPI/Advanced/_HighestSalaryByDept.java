import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class _HighestSalaryByDept {
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
            new Employee("Karan", "Sales", 90000),
            new Employee("Meera", "Engineering", 110000)
        );
        Map<String, Optional<Employee>> highestByDept = employees.stream()
            .collect(Collectors.groupingBy(
                e -> e.department,
                Collectors.maxBy((e1, e2) -> Double.compare(e1.salary, e2.salary))
            ));
        highestByDept.forEach((dept, emp) ->
            System.out.println(dept + ": " + emp.orElse(null))
        );
    }
}
