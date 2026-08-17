import java.time.LocalDateTime;

public class Transaction {
    public enum TransactionType {
        CREDIT, DEBIT
    }

    private final String transactionId;
    private final String accountNumber;
    private final double amount;
    private final TransactionType type;
    private final LocalDateTime timestamp;

    public Transaction(String transactionId, String accountNumber, double amount, TransactionType type, LocalDateTime timestamp) {
        if (transactionId == null || transactionId.trim().isEmpty()) {
            throw new IllegalArgumentException("Transaction ID cannot be null or empty.");
        }
        if (amount <= 0) {
            throw new IllegalArgumentException("Transaction amount must be greater than zero.");
        }
        this.transactionId = transactionId;
        this.accountNumber = accountNumber;
        this.amount = amount;
        this.type = type;
        this.timestamp = timestamp;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    @Override
    public String toString() {
        return String.format("Transaction{id='%s', account='%s', amount=%.2f, type=%s, timestamp=%s}",
                transactionId, accountNumber, amount, type, timestamp);
    }
}
