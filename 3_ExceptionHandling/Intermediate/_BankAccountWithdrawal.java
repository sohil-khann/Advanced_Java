import java.util.Scanner;

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

public class _BankAccountWithdrawal {
    private double balance;

    public _BankAccountWithdrawal(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Deposit amount must be positive.");
        }
        balance += amount;
        System.out.println("Deposited: " + amount);
        System.out.println("New balance: " + balance);
    }

    public void withdraw(double amount) throws InsufficientBalanceException, IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive.");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException(
                "Insufficient balance. Requested: " + amount + ", Available: " + balance);
        }
        balance -= amount;
        System.out.println("Withdrew: " + amount);
        System.out.println("Remaining balance: " + balance);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Bank Account Simulator ===");
        System.out.print("Enter initial deposit: ");
        double initial = scanner.nextDouble();
        _BankAccountWithdrawal account = new _BankAccountWithdrawal(initial);
        System.out.println("Account created. Current balance: " + account.getBalance());

        try {
            System.out.print("Enter deposit amount: ");
            double depositAmount = scanner.nextDouble();
            account.deposit(depositAmount);

            System.out.print("Enter withdrawal amount: ");
            double withdrawAmount = scanner.nextDouble();
            account.withdraw(withdrawAmount);
        } catch (InsufficientBalanceException e) {
            System.out.println("Transaction Failed: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid Input: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Unexpected error: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Final balance: " + account.getBalance());
            System.out.println("Session ended.");
        }
    }
}