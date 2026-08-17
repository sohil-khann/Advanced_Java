import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

class Student {
    private int id;
    private String name;
    private Map<LocalDate, Boolean> attendance;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.attendance = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void markAttendance(LocalDate date, boolean present) {
        attendance.put(date, present);
    }

    public double getAttendancePercentage() {
        if (attendance.isEmpty()) {
            return 0.0;
        }
        long presentCount = attendance.values().stream().filter(Boolean::booleanValue).count();
        return (presentCount * 100.0) / attendance.size();
    }

    public void displayAttendance() {
        System.out.println("Student: " + name + " (ID: " + id + ")");
        for (Map.Entry<LocalDate, Boolean> entry : attendance.entrySet()) {
            System.out.println(entry.getKey() + ": " + (entry.getValue() ? "Present" : "Absent"));
        }
        System.out.printf("Attendance Percentage: %.2f%%\n", getAttendancePercentage());
    }
}
