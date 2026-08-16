import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidateAadhaar {
    public static void main(String[] args) {
        String regex = "^\\d{4}\\s?\\d{4}\\s?\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Aadhaar number (12 digits, optional spaces): ");
        String input = scanner.nextLine().trim();
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            System.out.println("VALID: " + input + " is a valid Aadhaar number.");
        } else {
            System.out.println("INVALID: " + input + " is NOT a valid Aadhaar number.");
        }
        scanner.close();
    }
}
