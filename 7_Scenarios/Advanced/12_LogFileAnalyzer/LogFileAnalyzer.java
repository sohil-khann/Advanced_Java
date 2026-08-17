import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class LogFileAnalyzer {
    private static final DateTimeFormatter DT_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private static final Pattern LOG_PATTERN = Pattern.compile(
        "^(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2})\\s+(\\d+\\.\\d+\\.\\d+\\.\\d+)\\s+(INFO|WARN|ERROR|DEBUG)\\s+(.*)$"
    );
    private static final Pattern ERROR_PATTERN = Pattern.compile("ERROR: (.+)");

    public static void main(String[] args) {
        List<LogEntry> logs = generateTestLogs();
        System.out.println("Total logs parsed: " + logs.size());

        List<LogEntry> errors = logs.stream()
            .filter(log -> log.getLevel() == LogEntry.Level.ERROR)
            .collect(Collectors.toList());
        System.out.println("Total errors: " + errors.size());

        Map<String, Long> topErrors = errors.stream()
            .map(LogEntry::getMessage)
            .collect(Collectors.groupingBy(msg -> msg, Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(5)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));

        System.out.println("\nTop 5 Errors:");
        topErrors.forEach((k, v) -> System.out.println(v + " - " + k));

        Map<String, Long> errorsByIP = errors.stream()
            .collect(Collectors.groupingBy(LogEntry::getIpAddress, Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(5)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));

        System.out.println("\nTop 5 IPs with errors:");
        errorsByIP.forEach((ip, count) -> System.out.println(ip + " -> " + count + " errors"));
    }

    public static List<LogEntry> parseLogLine(String line) {
        List<LogEntry> entries = new ArrayList<>();
        Matcher matcher = LOG_PATTERN.matcher(line.trim());
        if (matcher.find()) {
            try {
                LocalDateTime ts = LocalDateTime.parse(matcher.group(1), DT_FORMATTER);
                String ip = matcher.group(2);
                LogEntry.Level level = LogEntry.Level.valueOf(matcher.group(3));
                String msg = matcher.group(4);
                entries.add(new LogEntry(ts, ip, level, msg));
            } catch (Exception e) {
                System.out.println("Parse error: " + line);
            }
        }
        return entries;
    }

    private static List<LogEntry> generateTestLogs() {
        List<LogEntry> logs = new ArrayList<>();
        String[] messages = {
            "INFO: User logged in",
            "ERROR: Database connection failed",
            "ERROR: NullPointerException at UserService",
            "WARN: High memory usage detected",
            "INFO: Order placed successfully",
            "ERROR: Payment timeout",
            "ERROR: Database connection failed",
            "DEBUG: Query executed in 45ms",
            "INFO: User logged out",
            "ERROR: Payment timeout"
        };
        Random rand = new Random();
        for (int i = 0; i < messages.length; i++) {
            String[] parts = messages[i].split(": ", 2);
            LogEntry.Level level = LogEntry.Level.valueOf(parts[0]);
            String msg = parts[1];
            String ip = "192.168.1." + rand.nextInt(255);
            LocalDateTime ts = LocalDateTime.now().minusMinutes(i);
            logs.add(new LogEntry(ts, ip, level, msg));
        }
        return logs;
    }
}
