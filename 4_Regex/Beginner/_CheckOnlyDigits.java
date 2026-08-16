import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _CheckOnlyDigits {
    public static void main(String[] args) {
        String regex = "^\\d+$";
        Pattern pattern = Pattern.compile(regex);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a string to check for digits only: ");
        String input = scanner.nextLine().trim();
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            System.out.println("RESULT: \"" + input + "\" contains ONLY digits.");
        } else {
            System.out.println("RESULT: \"" + input + "\" does NOT contain only digits.");
        }
        scanner.close();
    }
}
