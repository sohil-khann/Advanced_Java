public class FraudAlert {
    public enum Severity { LOW, MEDIUM, HIGH, CRITICAL }

    private static int idGen = 1;
    private final int alertId;
    private final String transactionId;
    private final Severity severity;
    private final String reason;
    private final java.time.LocalDateTime timestamp;

    public FraudAlert(String transactionId, Severity severity, String reason) {
        this.alertId = idGen++;
        this.transactionId = transactionId;
        this.severity = severity;
        this.reason = reason;
        this.timestamp = java.time.LocalDateTime.now();
    }

    public int getAlertId() { return alertId; }
    public String getTransactionId() { return transactionId; }
    public Severity getSeverity() { return severity; }
    public String getReason() { return reason; }
    public java.time.LocalDateTime getTimestamp() { return timestamp; }
}
