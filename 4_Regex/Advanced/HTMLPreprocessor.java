import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLPreprocessor {
    public static void main(String[] args) {
        String tagRegex = "<[^>]+>";
        String wsRegex = "\\s+";

        Pattern tagPattern = Pattern.compile(tagRegex);
        Pattern wsPattern = Pattern.compile(wsRegex);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Paste HTML content (end with empty line):");
        StringBuilder sb = new StringBuilder();
        while (true) {
            String line = scanner.nextLine();
            if (line.trim().isEmpty()) break;
            sb.append(line).append("\n");
        }
        String html = sb.toString();

        String text = tagPattern.matcher(html).replaceAll(" ");
        text = wsPattern.matcher(text).replaceAll(" ").trim();

        System.out.println("\n--- Cleaned Text ---");
        System.out.println(text);
        scanner.close();
    }
}
