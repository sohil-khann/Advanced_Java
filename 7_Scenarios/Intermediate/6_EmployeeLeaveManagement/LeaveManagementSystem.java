import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class LeaveManagementSystem {
    private Map<String, Employee> employees;
    private List<LeaveRequest> leaveRecords;

    public LeaveManagementSystem() {
        this.employees = new HashMap<>();
        this.leaveRecords = new ArrayList<>();
    }

    public void addEmployee(Employee employee) {
        employees.put(employee.getEmpId(), employee);
    }

    public Employee getEmployee(String empId) {
        return employees.get(empId);
    }

    public boolean isOverlapping(LeaveRequest r1, LeaveRequest r2) {
        return !r1.getEndDate().isBefore(r2.getStartDate()) && !r2.getEndDate().isBefore(r1.getStartDate());
    }

    public LeaveRequest applyLeave(String requestId, String empId, LocalDate startDate, LocalDate endDate, String leaveType) throws OverlappingLeaveException {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date.");
        }

        Employee employee = employees.get(empId);
        if (employee == null) {
            throw new IllegalArgumentException("Employee not found: " + empId);
        }

        long days = java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
        if (employee.getRemainingLeaves() < days) {
            throw new IllegalArgumentException("Insufficient leave balance. Remaining: " + employee.getRemainingLeaves() + ", Requested: " + days);
        }

        LeaveRequest newRequest = new LeaveRequest(requestId, employee, startDate, endDate, leaveType);

        for (LeaveRequest existing : leaveRecords) {
            if (existing.getEmployee().equals(employee) && existing.getStatus().equals("APPROVED")) {
                if (isOverlapping(newRequest, existing)) {
                    throw new OverlappingLeaveException("Leave request overlaps with existing approved leave: " + existing.getRequestId());
                }
            }
        }

        newRequest.setStatus("APPROVED");
        employee.incrementLeavesTaken((int) days);
        leaveRecords.add(newRequest);
        return newRequest;
    }

    public LeaveRequest cancelLeave(String requestId) {
        for (LeaveRequest request : leaveRecords) {
            if (request.getRequestId().equals(requestId) && request.getStatus().equals("APPROVED")) {
                long days = request.getNumberOfDays();
                request.getEmployee().decrementLeavesTaken((int) days);
                request.setStatus("CANCELLED");
                return request;
            }
        }
        return null;
    }

    public Map<String, Long> getLeaveStatsByDepartment() {
        return leaveRecords.stream()
                .filter(r -> r.getStatus().equals("APPROVED"))
                .collect(Collectors.groupingBy(r -> r.getEmployee().getDepartment(), Collectors.counting()));
    }

    public Map<String, Long> getLeaveStatsByType() {
        return leaveRecords.stream()
                .filter(r -> r.getStatus().equals("APPROVED"))
                .collect(Collectors.groupingBy(LeaveRequest::getLeaveType, Collectors.counting()));
    }

    public Map<String, Double> getAverageLeavesPerEmployee() {
        return employees.values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingInt(Employee::getLeavesTaken)));
    }

    public List<LeaveRequest> getApprovedLeavesForEmployee(String empId) {
        return leaveRecords.stream()
                .filter(r -> r.getEmployee().getEmpId().equals(empId) && r.getStatus().equals("APPROVED"))
                .collect(Collectors.toList());
    }

    public static class OverlappingLeaveException extends Exception {
        public OverlappingLeaveException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        LeaveManagementSystem system = new LeaveManagementSystem();

        Employee emp1 = new Employee("E001", "Priya", "Engineering", 20);
        Employee emp2 = new Employee("E002", "Rahul", "HR", 15);
        Employee emp3 = new Employee("E003", "Arjun", "Engineering", 20);

        system.addEmployee(emp1);
        system.addEmployee(emp2);
        system.addEmployee(emp3);

        try {
            system.applyLeave("L001", "E001", LocalDate.of(2025, 1, 10), LocalDate.of(2025, 1, 15), "CASUAL");
            system.applyLeave("L002", "E001", LocalDate.of(2025, 2, 1), LocalDate.of(2025, 2, 5), "SICK");
            system.applyLeave("L003", "E002", LocalDate.of(2025, 1, 20), LocalDate.of(2025, 1, 22), "CASUAL");
            system.applyLeave("L004", "E003", LocalDate.of(2025, 1, 12), LocalDate.of(2025, 1, 14), "CASUAL");

            try {
                system.applyLeave("L005", "E001", LocalDate.of(2025, 1, 13), LocalDate.of(2025, 1, 16), "CASUAL");
            } catch (OverlappingLeaveException e) {
                System.out.println("Overlap Rejected: " + e.getMessage());
            }

            System.out.println("Leave Stats by Department:");
            system.getLeaveStatsByDepartment().forEach((dept, count) ->
                    System.out.println(dept + ": " + count + " leaves"));

            System.out.println("\nLeave Stats by Type:");
            system.getLeaveStatsByType().forEach((type, count) ->
                    System.out.println(type + ": " + count + " leaves"));

            System.out.println("\nAverage Leaves per Department:");
            system.getAverageLeavesPerEmployee().forEach((dept, avg) ->
                    System.out.println(dept + ": " + String.format("%.2f", avg) + " days"));

            System.out.println("\nApproved Leaves for Priya:");
            system.getApprovedLeavesForEmployee("E001").forEach(System.out::println);

        } catch (OverlappingLeaveException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}

