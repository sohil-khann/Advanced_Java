import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _ValidateMobileNumber {
    public static void main(String[] args) {
        String regex = "^[6-9]\\d{9}$";
        Pattern pattern = Pattern.compile(regex);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter mobile number (10 digits starting with 6-9): ");
        String input = scanner.nextLine().trim();
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            System.out.println("VALID: " + input + " is a valid Indian mobile number.");
        } else {
            System.out.println("INVALID: " + input + " is NOT a valid Indian mobile number.");
        }
        scanner.close();
    }
}
