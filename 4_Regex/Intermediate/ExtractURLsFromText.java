import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractURLsFromText {
    public static void main(String[] args) {
        String regex = "https?://[\\w.-]+(?:/\\S*)?";
        Pattern pattern = Pattern.compile(regex);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter text (simulated file content) containing URLs:");
        String input = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
        Matcher matcher = pattern.matcher(input);
        System.out.println("Extracted URLs:");
        boolean found = false;
        while (matcher.find()) {
            found = true;
            System.out.println("  " + matcher.group());
        }
        if (!found) {
            System.out.println("  No URLs found.");
        }
        scanner.close();
    }
}
