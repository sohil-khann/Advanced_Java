import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ValidatePANCard {
    public static void main(String[] args) {
        String regex = "^[A-Z]{5}[0-9]{4}[A-Z]{1}$";
        Pattern pattern = Pattern.compile(regex);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter PAN card number (e.g., ABCDE1234F): ");
        String input = scanner.nextLine().trim().toUpperCase();
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            System.out.println("VALID: " + input + " is a valid PAN card number.");
        } else {
            System.out.println("INVALID: " + input + " is NOT a valid PAN card number.");
        }
        scanner.close();
    }
}
