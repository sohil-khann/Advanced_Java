import java.util.Scanner;

class InvalidNameException extends Exception {
    public InvalidNameException(String message) {
        super(message);
    }
}

class InvalidEmailException extends Exception {
    public InvalidEmailException(String message) {
        super(message);
    }
}

class InvalidAgeException extends Exception {
    public InvalidAgeException(String message) {
        super(message);
    }
}

public class _UserRegistrationValidation {
    public static void validateName(String name) throws InvalidNameException {
        if (name == null || name.trim().isEmpty()) {
            throw new InvalidNameException("Name cannot be empty.");
        }
    }

    public static void validateEmail(String email) throws InvalidEmailException {
        if (email == null || email.trim().isEmpty()) {
            throw new InvalidEmailException("Email cannot be empty.");
        }
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
            throw new InvalidEmailException("Invalid email format: " + email);
        }
    }

    public static void validateAge(int age) throws InvalidAgeException {
        if (age <= 18) {
            throw new InvalidAgeException("Age must be greater than 18. Provided: " + age);
        }
    }

    public static void registerUser(String name, String email, int age)
            throws InvalidNameException, InvalidEmailException, InvalidAgeException {
        validateName(name);
        validateEmail(email);
        validateAge(age);
        System.out.println("Registration Successful!");
        System.out.println("  Name: " + name);
        System.out.println("  Email: " + email);
        System.out.println("  Age: " + age);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== User Registration with Validation ===");

        try {
            System.out.print("Enter name: ");
            String name = scanner.nextLine();
            System.out.print("Enter email: ");
            String email = scanner.nextLine();
            System.out.print("Enter age: ");
            int age = scanner.nextInt();

            registerUser(name, email, age);
        } catch (InvalidNameException e) {
            System.out.println("Name Validation Error: " + e.getMessage());
        } catch (InvalidEmailException e) {
            System.out.println("Email Validation Error: " + e.getMessage());
        } catch (InvalidAgeException e) {
            System.out.println("Age Validation Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Registration process completed.");
        }
    }
}