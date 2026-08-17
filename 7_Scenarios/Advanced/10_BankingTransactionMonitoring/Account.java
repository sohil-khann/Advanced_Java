import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String accountNumber;
    private final String accountHolderName;
    private double balance;
    private final List<Transaction> transactions;

    private static final java.util.regex.Pattern ACCOUNT_NUMBER_PATTERN = java.util.regex.Pattern.compile("^\\d{12}$");

    public Account(String accountNumber, String accountHolderName, double balance) {
        if (!isValidAccountNumber(accountNumber)) {
            throw new IllegalArgumentException("Invalid account number. Must be a 12-digit numeric value.");
        }
        this.accountNumber = accountNumber;
        this.accountHolderName = accountHolderName;
        this.balance = balance;
        this.transactions = new ArrayList<>();
    }

    public static boolean isValidAccountNumber(String accountNumber) {
        return accountNumber != null && ACCOUNT_NUMBER_PATTERN.matcher(accountNumber).matches();
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountHolderName() {
        return accountHolderName;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }
}