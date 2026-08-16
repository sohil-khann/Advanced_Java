import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _ExtractNumbersFromText {
    public static void main(String[] args) {
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter text containing numbers: ");
        String input = scanner.nextLine();
        Matcher matcher = pattern.matcher(input);
        System.out.println("Extracted numbers:");
        boolean found = false;
        while (matcher.find()) {
            found = true;
            System.out.println("  " + matcher.group());
        }
        if (!found) {
            System.out.println("  No numbers found.");
        }
        scanner.close();
    }
}
