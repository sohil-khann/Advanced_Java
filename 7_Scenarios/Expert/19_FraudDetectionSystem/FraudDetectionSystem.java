import java.util.*;
import java.util.stream.Collectors;

public class FraudDetectionSystem {
    private static final List<Transaction> transactions = new ArrayList<>();
    private static final List<FraudAlert> alerts = new ArrayList<>();

    public static void main(String[] args) {
        transactions.addAll(Arrays.asList(
            new Transaction("U1", 500, "Amazon"),
            new Transaction("U1", 5000, "UnknownMerchant"),
            new Transaction("U2", 150, "Flipkart"),
            new Transaction("U1", 8000, "CryptoExchange"),
            new Transaction("U2", 20000, "LuxuryStore"),
            new Transaction("U1", 300, "Amazon"),
            new Transaction("U3", 100000, "UnknownMerchant")
        ));

        detectAnomalies();
        generateReport();
    }

    public static void detectAnomalies() {
        Map<String, List<Transaction>> byUser = transactions.stream()
            .collect(Collectors.groupingBy(Transaction::getUserId));

        byUser.forEach((userId, userTxns) -> {
            double avg = userTxns.stream().mapToDouble(Transaction::getAmount).average().orElse(0);
            userTxns.stream()
                .filter(t -> t.getAmount() > avg * 5)
                .forEach(t -> alerts.add(new FraudAlert(t.getTransactionId(), FraudAlert.Severity.HIGH,
                    "Amount " + t.getAmount() + " is unusually high for user")));
        });

        Set<String> riskyMerchants = new HashSet<>(Arrays.asList("UnknownMerchant", "CryptoExchange"));
        transactions.stream()
            .filter(t -> riskyMerchants.contains(t.getMerchant()))
            .forEach(t -> alerts.add(new FraudAlert(t.getTransactionId(), FraudAlert.Severity.MEDIUM,
                "Transaction with high-risk merchant: " + t.getMerchant())));

        System.out.println("Total alerts generated: " + alerts.size());
    }

    public static void generateReport() {
        System.out.println("\n=== Fraud Alert Report ===");
        alerts.forEach(a -> System.out.println(a.getAlertId() + " | " + a.getSeverity() + " | " + a.getReason()));
    }
}
