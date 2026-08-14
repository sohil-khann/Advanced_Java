import java.util.ArrayList;
import java.util.Scanner;

public class StudentManagementSystem {
    // Static nested Student class
    static class Student {
        int id;
        String name;
        int age;

        Student(int id, String name, int age) {
            this.id = id;
            this.name = name;
            this.age = age;
        }

        public String toString() {
            return \"ID: \" + id + \", Name: \" + name + \", Age: \" + age;
        }
    }

    static ArrayList<Student> students = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println(\"\n--- Student Management System ---\");
            System.out.println(\"1. Add Student\");
            System.out.println(\"2. View All Students\");
            System.out.println(\"3. Update Student\");
            System.out.println(\"4. Delete Student\");
            System.out.println(\"5. Exit\");
            System.out.print(\"Enter your choice: \");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    System.out.println(\"Exiting...\");
                    return;
                default:
                    System.out.println(\"Invalid choice!\");
            }
        }
    }

    static void addStudent() {
        System.out.print(\"Enter Student ID: \");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print(\"Enter Student Name: \");
        String name = scanner.nextLine();
        System.out.print(\"Enter Student Age: \");
        int age = scanner.nextInt();

        students.add(new Student(id, name, age));
        System.out.println(\"Student added successfully!\");
    }

    static void viewStudents() {
        if (students.isEmpty()) {
            System.out.println(\"No students found.\");
            return;
        }
        for (Student s : students) {
            System.out.println(s);
        }
    }

    static void updateStudent() {
        System.out.print(\"Enter Student ID to update: \");
        int id = scanner.nextInt();
        scanner.nextLine();

        for (Student s : students) {
            if (s.id == id) {
                System.out.print(\"Enter new Name: \");
                s.name = scanner.nextLine();
                System.out.print(\"Enter new Age: \");
                s.age = scanner.nextInt();
                System.out.println(\"Student updated successfully!\");
                return;
            }
        }
        System.out.println(\"Student not found!\");
    }

    static void deleteStudent() {
        System.out.print(\"Enter Student ID to delete: \");
        int id = scanner.nextInt();

        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).id == id) {
                students.remove(i);
                System.out.println(\"Student deleted successfully!\");
                return;
            }
        }
        System.out.println(\"Student not found!\");
    }
}
