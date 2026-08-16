import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _ValidateEmail {
    public static void main(String[] args) {
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
        Pattern pattern = Pattern.compile(regex);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter email address: ");
        String input = scanner.nextLine().trim();
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            System.out.println("VALID: " + input + " is a valid email address.");
        } else {
            System.out.println("INVALID: " + input + " is NOT a valid email address.");
        }
        scanner.close();
    }
}
