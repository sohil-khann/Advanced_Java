import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupEmployeesByDepartment {
    static class Employee {
        int id;
        String name;
        String department;
        double salary;

        Employee(int id, String name, String department, double salary) {
            this.id = id;
            this.name = name;
            this.department = department;
            this.salary = salary;
        }

        public String toString() {
            return  ID:  + id +  Name:  + name +  Salary:  + salary;
        }
    }

    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,  Priya, HR, 50000));
        employees.add(new Employee(2, Rahul, IT, 70000));
        employees.add(new Employee(3, Arjun, HR, 55000));
        employees.add(new Employee(4, Anjali, IT, 75000));
        employees.add(new Employee(5, Vikram, Finance, 65000));

        HashMap<String, List<Employee>> departmentMap = new HashMap<>();

        for (Employee emp : employees) {
            departmentMap.computeIfAbsent(emp.department, k -> new ArrayList<>()).add(emp);
        }

        System.out.println(Employees grouped by department:);
        for (Map.Entry<String, List<Employee>> entry : departmentMap.entrySet()) {
            System.out.println(\\nDepartment:  + entry.getKey());
            for (Employee emp : entry.getValue()) {
                System.out.println(  + emp);
            }
        }
    }
}