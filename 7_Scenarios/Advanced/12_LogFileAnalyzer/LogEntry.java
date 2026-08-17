import java.time.LocalDateTime;

public class LogEntry {
    public enum Level { INFO, WARN, ERROR, DEBUG }

    private final LocalDateTime timestamp;
    private final String ipAddress;
    private final Level level;
    private final String message;

    public LogEntry(LocalDateTime timestamp, String ipAddress, Level level, String message) {
        this.timestamp = timestamp;
        this.ipAddress = ipAddress;
        this.level = level;
        this.message = message;
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public String getIpAddress() { return ipAddress; }
    public Level getLevel() { return level; }
    public String getMessage() { return message; }

    @Override
    public String toString() {
        return String.format("[%s] %s %s: %s", timestamp, level, ipAddress, message);
    }
}
