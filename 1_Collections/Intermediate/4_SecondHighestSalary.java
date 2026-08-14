import java.util.ArrayList;

public class SecondHighestSalary {
    static class Employee {
        int id;
        String name;
        double salary;

        Employee(int id, String name, double salary) {
            this.id = id;
            this.name = name;
            this.salary = salary;
        }
    }

    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();
        employees.add(new Employee(1,  Priya, 50000));
        employees.add(new Employee(2, Rahul, 70000));
        employees.add(new Employee(3, Arjun, 70000));
        employees.add(new Employee(4, Anjali, 60000));
        employees.add(new Employee(5, Vikram, 80000));

        double highest = Double.MIN_VALUE;
        double secondHighest = Double.MIN_VALUE;

        for (Employee emp : employees) {
            if (emp.salary > highest) {
                secondHighest = highest;
                highest = emp.salary;
            } else if (emp.salary > secondHighest && emp.salary < highest) {
                secondHighest = emp.salary;
            }
        }

        if (secondHighest == Double.MIN_VALUE) {
            System.out.println(No second highest salary found - all salaries may be equal.);
        } else {
            System.out.println(Highest Salary:  + highest);
            System.out.println( Second Highest Salary:  + secondHighest);
        }
    }
}