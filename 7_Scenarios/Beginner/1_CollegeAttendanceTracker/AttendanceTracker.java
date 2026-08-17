import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AttendanceTracker {
    private List<Student> students;

    public AttendanceTracker() {
        students = new ArrayList<>();
    }

    public void addStudent(int id, String name) throws InvalidStudentIdException {
        if (id <= 0) {
            throw new InvalidStudentIdException("Student ID must be positive.");
        }
        for (Student s : students) {
            if (s.getId() == id) {
                throw new InvalidStudentIdException("Student ID already exists.");
            }
        }
        students.add(new Student(id, name));
    }

    public Student findStudent(int id) throws InvalidStudentIdException {
        for (Student s : students) {
            if (s.getId() == id) {
                return s;
            }
        }
        throw new InvalidStudentIdException("Student not found with ID: " + id);
    }

    public void markAttendance(int id, LocalDate date, boolean present) {
        try {
            Student student = findStudent(id);
            student.markAttendance(date, present);
            System.out.println("Attendance marked for " + student.getName());
        } catch (InvalidStudentIdException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void displayAllAttendance() {
        if (students.isEmpty()) {
            System.out.println("No students available.");
            return;
        }
        for (Student s : students) {
            s.displayAttendance();
            System.out.println();
        }
    }

    public static void main(String[] args) {
        AttendanceTracker tracker = new AttendanceTracker();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- College Attendance Tracker ---");
            System.out.println("1. Add Student");
            System.out.println("2. Mark Attendance");
            System.out.println("3. Display Attendance");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Student ID: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    try {
                        tracker.addStudent(id, name);
                        System.out.println("Student added successfully.");
                    } catch (InvalidStudentIdException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "2":
                    System.out.print("Enter Student ID: ");
                    int sid = Integer.parseInt(scanner.nextLine());
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    LocalDate date = LocalDate.parse(scanner.nextLine());
                    System.out.print("Is Present? (true/false): ");
                    boolean present = Boolean.parseBoolean(scanner.nextLine());
                    tracker.markAttendance(sid, date, present);
                    break;
                case "3":
                    tracker.displayAllAttendance();
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        scanner.close();
    }
}
