import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ChatbotInputValidator {
    public static void main(String[] args) {
        String commandRegex = "^/(\\w+)";
        String mentionRegex = "@\\w+";
        String urlRegex = "https?://\\S+";
        String emojiRegex = "[\\uD800-\\uDBFF][\\uDC00-\\uDFFF]";
        String profanityRegex = "(?i)\\b(?:badword|damn|stupid)\\b";

        Pattern commandPattern = Pattern.compile(commandRegex);
        Pattern mentionPattern = Pattern.compile(mentionRegex);
        Pattern urlPattern = Pattern.compile(urlRegex);
        Pattern emojiPattern = Pattern.compile(emojiRegex);
        Pattern profanityPattern = Pattern.compile(profanityRegex);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter chatbot message: ");
        String input = scanner.nextLine();

        System.out.println("\\n--- Analysis ---");
        System.out.println("Commands:");
        Matcher cmd = commandPattern.matcher(input);
        while (cmd.find()) System.out.println("  " + cmd.group());

        System.out.println("Mentions:");
        Matcher mention = mentionPattern.matcher(input);
        while (mention.find()) System.out.println("  " + mention.group());

        System.out.println("URLs:");
        Matcher url = urlPattern.matcher(input);
        while (url.find()) System.out.println("  " + url.group());

        System.out.println("Emojis:");
        Matcher emoji = emojiPattern.matcher(input);
        while (emoji.find()) System.out.println("  " + emoji.group());

        System.out.println("Profanity:");
        Matcher profanity = profanityPattern.matcher(input);
        if (profanity.find()) {
            System.out.println("  WARNING: Profanity detected!");
        } else {
            System.out.println("  None detected.");
        }
        scanner.close();
    }
}
