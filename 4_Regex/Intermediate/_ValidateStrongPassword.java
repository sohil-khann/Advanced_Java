import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _ValidateStrongPassword {
    public static void main(String[] args) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter password to validate: ");
        String input = scanner.nextLine();
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            System.out.println("VALID: Password is strong.");
        } else {
            System.out.println("INVALID: Password is NOT strong.");
            System.out.println("Requirements: min 8 chars, at least one uppercase, one lowercase, one digit, one special character.");
        }
        scanner.close();
    }
}
