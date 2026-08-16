import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class _CountVowelsUsingRegex {
    public static void main(String[] args) {
        String regex = "[AEIOUaeiou]";
        Pattern pattern = Pattern.compile(regex);
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a sentence: ");
        String input = scanner.nextLine();
        Matcher matcher = pattern.matcher(input);
        int count = 0;
        while (matcher.find()) {
            count++;
        }
        System.out.println("Input: " + input);
        System.out.println("Number of vowels found: " + count);
        scanner.close();
    }
}
