import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class QuizSystem {
    public static void main(String[] args) {
        Quiz quiz = new Quiz();
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            System.out.println("\n--- Online Quiz System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Add Score");
            System.out.println("3. Display Scores");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    quiz.addStudent(new Student(name));
                    System.out.println("Student added.");
                    break;
                case "2":
                    if (quiz.getStudents().isEmpty()) {
                        System.out.println("No students available. Add students first.");
                        break;
                    }
                    System.out.print("Enter Student Name: ");
                    String sname = scanner.nextLine();
                    System.out.print("Enter Score (0-100): ");
                    int score = Integer.parseInt(scanner.nextLine());
                    try {
                        Optional<Student> opt = quiz.getStudents().stream()
                                .filter(s -> s.getName().equalsIgnoreCase(sname))
                                .findFirst();
                        if (!opt.isPresent()) {
                            System.out.println("Student not found.");
                        } else {
                            quiz.addScore(opt.get(), score);
                            System.out.println("Score recorded.");
                        }
                    } catch (InvalidMarksException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case "3":
                    quiz.displayScores();
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
