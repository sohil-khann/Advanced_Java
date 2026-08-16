import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ExtractHashtags {
    public static void main(String[] args) {
        String regex = "#\\w+";
        Pattern pattern = Pattern.compile(regex);
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter social media post text:");
        String input = scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "";
        Matcher matcher = pattern.matcher(input);
        System.out.println("Extracted hashtags:");
        boolean found = false;
        while (matcher.find()) {
            found = true;
            System.out.println("  " + matcher.group());
        }
        if (!found) {
            System.out.println("  No hashtags found.");
        }
        scanner.close();
    }
}
