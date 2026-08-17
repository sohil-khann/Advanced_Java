import java.time.LocalDateTime;

public class Transaction {
    public enum Status { INITIATED, SUCCESS, FAILED, RETRYING }

    private static int idGen = 1;
    private final int txnId;
    private final String upiId;
    private final double amount;
    private final String payee;
    private Status status;
    private final LocalDateTime timestamp;
    private int retryCount;

    public Transaction(String upiId, double amount, String payee) {
        this.txnId = idGen++;
        this.upiId = upiId;
        this.amount = amount;
        this.payee = payee;
        this.status = Status.INITIATED;
        this.timestamp = LocalDateTime.now();
        this.retryCount = 0;
    }

    public int getTxnId() { return txnId; }
    public String getUpiId() { return upiId; }
    public double getAmount() { return amount; }
    public String getPayee() { return payee; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public int getRetryCount() { return retryCount; }
    public void incrementRetry() { retryCount++; }
}
