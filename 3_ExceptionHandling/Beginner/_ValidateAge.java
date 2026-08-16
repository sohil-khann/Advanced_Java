import java.util.Scanner;

public class _ValidateAge {
    public static void validateAge(int age) throws IllegalArgumentException {
        if (age < 0 || age > 150) {
            throw new IllegalArgumentException("Invalid age: " + age + ". Age must be between 0 and 150.");
        }
        System.out.println("Valid age entered: " + age);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Age Validator ===");
        System.out.print("Enter your age: ");

        try {
            int age = scanner.nextInt();
            validateAge(age);
        } catch (IllegalArgumentException e) {
            System.out.println("Validation Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a valid integer age.");
        } finally {
            scanner.close();
            System.out.println("Age validation process finished.");
        }
    }
}