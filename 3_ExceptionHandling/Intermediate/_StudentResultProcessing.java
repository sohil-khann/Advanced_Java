import java.util.InputMismatchException;
import java.util.Scanner;

class InvalidMarksException extends Exception {
    public InvalidMarksException(String message) {
        super(message);
    }
}

class StudentNullException extends Exception {
    public StudentNullException(String message) {
        super(message);
    }
}

public class _StudentResultProcessing {
    public static double calculatePercentage(int[] marks) throws InvalidMarksException, ArithmeticException {
        if (marks == null || marks.length == 0) {
            throw new ArithmeticException("Cannot calculate percentage for empty marks array.");
        }
        int total = 0;
        for (int mark : marks) {
            if (mark < 0 || mark > 100) {
                throw new InvalidMarksException("Invalid mark: " + mark + ". Marks must be between 0 and 100.");
            }
            total += mark;
        }
        return (double) total / marks.length;
    }

    public static void processStudent(String name, int[] marks)
            throws StudentNullException, InvalidMarksException, ArithmeticException {
        if (name == null || name.trim().isEmpty()) {
            throw new StudentNullException("Student name is null or empty.");
        }
        double percentage = calculatePercentage(marks);
        char grade;
        if (percentage >= 90) {
            grade = 'A';
        } else if (percentage >= 75) {
            grade = 'B';
        } else if (percentage >= 60) {
            grade = 'C';
        } else if (percentage >= 40) {
            grade = 'D';
        } else {
            grade = 'F';
        }
        System.out.println("--- Student Result ---");
        System.out.println("Name: " + name);
        System.out.println("Percentage: " + String.format("%.2f%%", percentage));
        System.out.println("Grade: " + grade);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Student Result Processing ===");

        try {
            System.out.print("Enter student name: ");
            String name = scanner.nextLine();
            System.out.print("Enter number of subjects: ");
            int n = scanner.nextInt();

            if (n <= 0) {
                throw new InvalidMarksException("Number of subjects must be greater than zero.");
            }

            int[] marks = new int[n];
            for (int i = 0; i < n; i++) {
                System.out.print("Enter mark for subject " + (i + 1) + ": ");
                marks[i] = scanner.nextInt();
            }
            processStudent(name, marks);
        } catch (StudentNullException e) {
            System.out.println("Student Error: " + e.getMessage());
        } catch (InvalidMarksException e) {
            System.out.println("Marks Error: " + e.getMessage());
        } catch (ArithmeticException e) {
            System.out.println("Calculation Error: " + e.getMessage());
        } catch (InputMismatchException e) {
            System.out.println("Input Error: Please enter valid numeric values.");
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Result processing completed.");
        }
    }
}