import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class DistributedLoggingFramework {
    public enum LogLevel {
        INFO, WARN, ERROR
    }

    public static class LogEntry {
        private LogLevel level;
        private String message;
        private LocalDateTime timestamp;

        public LogEntry(LogLevel level, String message) {
            this.level = level;
            this.message = message;
            this.timestamp = LocalDateTime.now();
        }

        @Override
        public String toString() {
            return timestamp.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME) + " [" + level + "] " + message;
        }
    }

    private String logFilePath;
    private FileWriter writer;

    public DistributedLoggingFramework(String logFilePath) {
        this.logFilePath = logFilePath;
        try {
            File file = new File(logFilePath);
            file.getParentFile().mkdirs();
            this.writer = new FileWriter(file, true);
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    public void log(LogLevel level, String message) {
        LogEntry entry = new LogEntry(level, message);
        String entryStr = entry.toString() + System.lineSeparator();

        if (writer == null) {
            System.err.println("Logger not initialized. Log entry: " + entryStr);
            return;
        }

        try {
            writer.write(entryStr);
            writer.flush();
        } catch (IOException e) {
            handleLoggingFailure(entryStr, e);
        }
    }

    private void handleLoggingFailure(String entry, IOException e) {
        String cause = e.getMessage();
        if (cause != null && (cause.contains("space") || cause.contains("disk") || cause.toLowerCase().contains("no space"))) {
            System.err.println("Disk full. Cannot write log: " + entry.trim());
        } else if (cause != null && (cause.contains("permission") || cause.toLowerCase().contains("access"))) {
            System.err.println("Permission denied. Cannot write log: " + entry.trim());
        } else {
            System.err.println("Logging failed: " + e.getMessage());
        }
    }

    public void close() {
        try {
            if (writer != null) {
                writer.close();
            }
        } catch (IOException e) {
            System.err.println("Failed to close logger: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        String logFile = "D:\\Edelweiss\\Advanced_Java\\3_ExceptionHandling\\Advanced\\4_DistributedLoggingFramework\\logs\\app.log";
        DistributedLoggingFramework logger = new DistributedLoggingFramework(logFile);

        logger.log(LogLevel.INFO, "Application started");
        logger.log(LogLevel.WARN, "High memory usage detected");
        logger.log(LogLevel.ERROR, "Database connection failed");

        logger.close();
    }
}
