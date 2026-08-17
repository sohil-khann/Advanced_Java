import java.util.Objects;

public class Employee {
    private String empId;
    private String name;
    private String department;
    private int totalLeaves;
    private int leavesTaken;

    public Employee(String empId, String name, String department, int totalLeaves) {
        this.empId = empId;
        this.name = name;
        this.department = department;
        this.totalLeaves = totalLeaves;
        this.leavesTaken = 0;
    }

    public String getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public int getTotalLeaves() {
        return totalLeaves;
    }

    public int getLeavesTaken() {
        return leavesTaken;
    }

    public int getRemainingLeaves() {
        return totalLeaves - leavesTaken;
    }

    public void incrementLeavesTaken(int days) {
        this.leavesTaken += days;
    }

    public void decrementLeavesTaken(int days) {
        this.leavesTaken = Math.max(0, this.leavesTaken - days);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "empId='" + empId + '\'' +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", leavesTaken=" + leavesTaken +
                ", remainingLeaves=" + getRemainingLeaves() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return Objects.equals(empId, employee.empId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(empId);
    }
}