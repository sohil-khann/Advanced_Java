import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogAnalyzer {
    public static void main(String[] args) {
        String ipRegex = "\\b(?:\\d{1,3}\\.){3}\\d{1,3}\\\\\\b";
        String timestampRegex = "\\d{4}-\\d{2}-\\d{2}[T ]\\d{2}:\\d{2}:\\d{2}(?:[.\\d]*Z?)?";
        Pattern ipPattern = Pattern.compile(ipRegex);
        Pattern tsPattern = Pattern.compile(timestampRegex);

        String sampleLog = "[2025-01-15T10:23:45Z] INFO 192.168.1.1 Request processed " +
                           "[2025-01-15T10:24:01.123Z] ERROR 10.0.0.5 Connection timeout " +
                           "[2025-01-15 10:25:30] DEBUG 172.16.0.9 Health check passed";

        Scanner scanner = new Scanner(System.in);
        System.out.println("Sample Log:");
        System.out.println(sampleLog);
        System.out.println();

        Matcher ipMatcher = ipPattern.matcher(sampleLog);
        System.out.println("IP Addresses found:");
        while (ipMatcher.find()) {
            System.out.println("  " + ipMatcher.group());
        }

        Matcher tsMatcher = tsPattern.matcher(sampleLog);
        System.out.println("Timestamps found:");
        while (tsMatcher.find()) {
            System.out.println("  " + tsMatcher.group());
        }
        scanner.close();
    }
}
