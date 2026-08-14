import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class _TransactionAnalysisSuspicious {
    static class Transaction {
        int id;
        String accountNumber;
        double amount;
        LocalDateTime timestamp;
        Transaction(int id, String accountNumber, double amount, LocalDateTime timestamp) {
            this.id = id;
            this.accountNumber = accountNumber;
            this.amount = amount;
            this.timestamp = timestamp;
        }
        public String toString() {
            return "Txn" + id + " | " + accountNumber + " | \u20B9" + amount + " | " + timestamp;
        }
    }
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        List<Transaction> transactions = Arrays.asList(
            new Transaction(1, "ACC001", 45000, now.minus(10, ChronoUnit.MINUTES)),
            new Transaction(2, "ACC002", 15000, now.minus(8, ChronoUnit.MINUTES)),
            new Transaction(3, "ACC001", 60000, now.minus(5, ChronoUnit.MINUTES)),
            new Transaction(4, "ACC003", 2500, now.minus(2, ChronoUnit.MINUTES)),
            new Transaction(5, "ACC001", 55000, now.minus(1, ChronoUnit.MINUTES)),
            new Transaction(6, "ACC002", 80000, now.minus(20, ChronoUnit.MINUTES)),
            new Transaction(7, "ACC003", 500000, now.minus(3, ChronoUnit.MINUTES))
        );
        double threshold = 50000;
        List<Transaction> suspicious = transactions.stream()
            .filter(t -> t.amount > threshold)
            .collect(Collectors.toList());
        System.out.println("Transactions above \u20B9" + threshold + ":");
        suspicious.forEach(System.out::println);
        List<String> multipleTxAccounts = transactions.stream()
            .collect(Collectors.groupingBy(t -> t.accountNumber))
            .entrySet().stream()
            .filter(e -> e.getValue().size() > 1)
            .map(e -> e.getKey())
            .toList();
        System.out.println("\nAccounts with multiple transactions: " + multipleTxAccounts);
    }
}
