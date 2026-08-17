import java.time.LocalDateTime;

public class Transaction {
    private static int idGen = 1;
    private final String transactionId;
    private final String userId;
    private final double amount;
    private final String merchant;
    private final LocalDateTime timestamp;

    public Transaction(String userId, double amount, String merchant) {
        this.transactionId = "TXN" + String.format("%06d", idGen++);
        this.userId = userId;
        this.amount = amount;
        this.merchant = merchant;
        this.timestamp = LocalDateTime.now();
    }

    public String getTransactionId() { return transactionId; }
    public String getUserId() { return userId; }
    public double getAmount() { return amount; }
    public String getMerchant() { return merchant; }
    public LocalDateTime getTimestamp() { return timestamp; }
}
