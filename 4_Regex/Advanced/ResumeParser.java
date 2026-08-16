import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ResumeParser {
    public static void main(String[] args) {
        String emailRegex = "[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}";
        String phoneRegex = "[6-9]\\d{9}";
        String skillsRegex = "(Java|Python|SQL|JavaScript|AWS|Docker|Kubernetes|React|Spring)";

        Pattern emailPattern = Pattern.compile(emailRegex);
        Pattern phonePattern = Pattern.compile(phoneRegex);
        Pattern skillsPattern = Pattern.compile(skillsRegex);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Paste resume text (end with empty line):");
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break;
            sb.append(line).append("\n");
        }
        String resume = sb.toString();

        System.out.println("\n--- Parsed Resume ---");
        System.out.println("Emails:");
        Matcher emailMatcher = emailPattern.matcher(resume);
        boolean found = false;
        while (emailMatcher.find()) {
            found = true;
            System.out.println("  " + emailMatcher.group());
        }
        if (!found) System.out.println("  None found.");

        System.out.println("Phone Numbers (Indian format):");
        Matcher phoneMatcher = phonePattern.matcher(resume);
        found = false;
        while (phoneMatcher.find()) {
            found = true;
            System.out.println("  " + phoneMatcher.group());
        }
        if (!found) System.out.println("  None found.");

        System.out.println("Skills detected:");
        Matcher skillsMatcher = skillsPattern.matcher(resume);
        found = false;
        while (skillsMatcher.find()) {
            found = true;
            System.out.println("  " + skillsMatcher.group());
        }
        if (!found) System.out.println("  None found.");
        scanner.close();
    }
}
