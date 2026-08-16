import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SyntaxChecker {
    public static void main(String[] args) {
        String regex = "^[A-Z]+(?:\\s+\\S+)*(?:\\s+--\\w+=\\S+)?$";
        Pattern pattern = Pattern.compile(regex);

        String[] samples = {
            "RUN server --host=localhost",
            "STOP worker1",
            "DELETE file.txt",
            "invalid command --flag=value",
            "run --flag=value",
            "START --flag=value extra"
        };

        for (String sample : samples) {
            Matcher matcher = pattern.matcher(sample.trim());
            if (matcher.matches()) {
                System.out.println("VALID: " + sample);
            } else {
                System.out.println("INVALID: " + sample);
            }
        }

        Scanner scanner = new Scanner(System.in);
        System.out.print("\\nEnter command to validate: ");
        String input = scanner.nextLine().trim();
        Matcher matcher = pattern.matcher(input);
        if (matcher.matches()) {
            System.out.println("VALID: " + input);
        } else {
            System.out.println("INVALID: " + input);
        }
        scanner.close();
    }
}
