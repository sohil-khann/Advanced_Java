import java.time.LocalDate;
import java.util.Objects;

public class LeaveRequest {
    private String requestId;
    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private String leaveType;
    private String status;

    public LeaveRequest(String requestId, Employee employee, LocalDate startDate, LocalDate endDate, String leaveType) {
        this.requestId = requestId;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.leaveType = leaveType;
        this.status = "PENDING";
    }

    public String getRequestId() {
        return requestId;
    }

    public Employee getEmployee() {
        return employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public String getLeaveType() {
        return leaveType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getNumberOfDays() {
        return java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate) + 1;
    }

    @Override
    public String toString() {
        return "LeaveRequest{" +
                "requestId='" + requestId + '\'' +
                ", employee=" + employee.getName() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", leaveType='" + leaveType + '\'' +
                ", status='" + status + '\'' +
                ", days=" + getNumberOfDays() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeaveRequest that = (LeaveRequest) o;
        return Objects.equals(requestId, that.requestId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId);
    }
}