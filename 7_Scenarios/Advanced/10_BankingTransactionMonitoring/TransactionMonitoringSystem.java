import java.util.*;
import java.time.LocalDateTime;
import java.util.stream.Collectors;

public class TransactionMonitoringSystem {
    private static final double HIGH_VALUE_THRESHOLD = 50000.0;
    private static final int SHORT_DURATION_MINUTES = 5;
    private static final int RAPID_TRANSACTION_COUNT_THRESHOLD = 3;

    private final List<Transaction> transactions;
    private final Map<String, Account> accounts;

    public TransactionMonitoringSystem() {
        this.transactions = new ArrayList<>();
        this.accounts = new HashMap<>();
    }

    public void addAccount(Account account) {
        if (account == null) {
            throw new IllegalArgumentException("Account cannot be null.");
        }
        accounts.put(account.getAccountNumber(), account);
    }

    public void addTransaction(Transaction transaction) throws SuspiciousTransactionException {
        if (transaction == null) {
            throw new IllegalArgumentException("Transaction cannot be null.");
        }

        Account account = accounts.get(transaction.getAccountNumber());
        if (account == null) {
            throw new IllegalArgumentException("Account not found for transaction: " + transaction.getTransactionId());
        }

        if (transaction.getAmount() > HIGH_VALUE_THRESHOLD) {
            throw new SuspiciousTransactionException(
                String.format("High-value transaction detected: %s (Amount: %.2f)", transaction.getTransactionId(), transaction.getAmount())
            );
        }

        List<Transaction> rapidTransactions = findRapidTransactions(transaction);
        if (rapidTransactions.size() >= RAPID_TRANSACTION_COUNT_THRESHOLD - 1) {
            throw new SuspiciousTransactionException(
                String.format("Suspicious rapid transactions detected for account %s: %d existing transactions within %d minutes of current transaction",
                    transaction.getAccountNumber(), rapidTransactions.size(), SHORT_DURATION_MINUTES)
            );
        }

        transactions.add(transaction);
        account.addTransaction(transaction);
    }

    public List<Transaction> findHighValueTransactions() {
        return transactions.stream()
                .filter(t -> t.getAmount() > HIGH_VALUE_THRESHOLD)
                .collect(Collectors.toList());
    }

    public List<Transaction> findRapidTransactions(Transaction currentTransaction) {
        String accountNumber = currentTransaction.getAccountNumber();
        LocalDateTime windowStart = currentTransaction.getTimestamp().minusMinutes(SHORT_DURATION_MINUTES);
        LocalDateTime windowEnd = currentTransaction.getTimestamp().plusMinutes(SHORT_DURATION_MINUTES);

        return transactions.stream()
                .filter(t -> t.getAccountNumber().equals(accountNumber))
                .filter(t -> t.getTimestamp().isAfter(windowStart) && t.getTimestamp().isBefore(windowEnd))
                .collect(Collectors.toList());
    }

    public List<Transaction> getAllTransactionsForAccount(String accountNumber) {
        return transactions.stream()
                .filter(t -> t.getAccountNumber().equals(accountNumber))
                .sorted(Comparator.comparing(Transaction::getTimestamp))
                .collect(Collectors.toList());
    }

    public void generateReport() {
        System.out.println("============================================================");
        System.out.println("           BANKING TRANSACTION MONITORING REPORT");
        System.out.println("============================================================");
        System.out.println();

        System.out.println("Total Accounts: " + accounts.size());
        System.out.println("Total Transactions Processed: " + transactions.size());
        System.out.println();

        System.out.println("------------------------------------------------------------");
        System.out.println("HIGH-VALUE TRANSACTIONS (> " + HIGH_VALUE_THRESHOLD + ")");
        System.out.println("------------------------------------------------------------");
        List<Transaction> highValue = findHighValueTransactions();
        if (highValue.isEmpty()) {
            System.out.println("No high-value transactions found.");
        } else {
            highValue.forEach(t -> System.out.println(t));
        }
        System.out.println();

        System.out.println("------------------------------------------------------------");
        System.out.println("ACCOUNT WISE TRANSACTION SUMMARY");
        System.out.println("------------------------------------------------------------");
        for (Account acc : accounts.values()) {
            System.out.println("Account: " + acc.getAccountNumber() + " | Holder: " + acc.getAccountHolderName());
            System.out.println("  Balance: " + String.format("%.2f", acc.getBalance()));
            List<Transaction> accountTxns = getAllTransactionsForAccount(acc.getAccountNumber());
            System.out.println("  Transactions: " + accountTxns.size());
            accountTxns.forEach(t -> System.out.println("    - " + t));
            System.out.println();
        }

        System.out.println("============================================================");
        System.out.println("Report generated at: " + java.time.LocalDateTime.now());
        System.out.println("============================================================");
    }

    public static void main(String[] args) {
        TransactionMonitoringSystem system = new TransactionMonitoringSystem();

        try {
            Account acc1 = new Account("123456789012", "Priya", 100000.0);
            Account acc2 = new Account("987654321098", "Rahul Sharma", 250000.0);
            Account acc3 = new Account("111222333444", "Arjun", 50000.0);

            system.addAccount(acc1);
            system.addAccount(acc2);
            system.addAccount(acc3);

            java.time.LocalDateTime now = java.time.LocalDateTime.now();

            Transaction t1 = new Transaction("TXN001", "123456789012", 5000.0, Transaction.TransactionType.DEBIT, now.minusMinutes(10));
            Transaction t2 = new Transaction("TXN002", "123456789012", 12000.0, Transaction.TransactionType.CREDIT, now.minusMinutes(8));
            Transaction t3 = new Transaction("TXN003", "123456789012", 8000.0, Transaction.TransactionType.DEBIT, now.minusMinutes(6));
            Transaction t4 = new Transaction("TXN004", "123456789012", 15000.0, Transaction.TransactionType.CREDIT, now.minusMinutes(4));

            Transaction t5 = new Transaction("TXN005", "987654321098", 60000.0, Transaction.TransactionType.CREDIT, now.minusMinutes(15));

            Transaction t6 = new Transaction("TXN006", "111222333444", 3000.0, Transaction.TransactionType.DEBIT, now.minusMinutes(20));
            Transaction t7 = new Transaction("TXN007", "111222333444", 4500.0, Transaction.TransactionType.CREDIT, now.minusMinutes(18));

            List<Transaction> allTransactions = Arrays.asList(t1, t2, t3, t4, t5, t6, t7);

            for (Transaction t : allTransactions) {
                try {
                    system.addTransaction(t);
                    System.out.println("Processed: " + t.getTransactionId());
                } catch (SuspiciousTransactionException e) {
                    System.out.println("SUSPICIOUS TRANSACTION BLOCKED: " + e.getMessage());
                }
            }

            System.out.println();
            system.generateReport();

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
