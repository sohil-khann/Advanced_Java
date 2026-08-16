import java.util.Scanner;

public class ATMSimulation {
    private static class Account {
        private double balance;
        private String pin;
        private double dailyWithdrawalLimit;
        private double dailyWithdrawn;

        public Account(double balance, String pin, double dailyWithdrawalLimit) {
            this.balance = balance;
            this.pin = pin;
            this.dailyWithdrawalLimit = dailyWithdrawalLimit;
            this.dailyWithdrawn = 0.0;
        }

        public double getBalance() {
            return balance;
        }

        public void deposit(double amount) {
            this.balance += amount;
        }

        public void withdraw(double amount) throws InsufficientFundsException, TransactionLimitException {
            if (amount > balance) {
                throw new InsufficientFundsException("Insufficient funds. Available: " + balance);
            }
            if (dailyWithdrawn + amount > dailyWithdrawalLimit) {
                throw new TransactionLimitException("Daily limit exceeded. Remaining: " + (dailyWithdrawalLimit - dailyWithdrawn));
            }
            this.balance -= amount;
            this.dailyWithdrawn += amount;
        }

        public boolean validatePin(String inputPin) throws InvalidPinException {
            if (!this.pin.equals(inputPin)) {
                throw new InvalidPinException("Invalid PIN");
            }
            return true;
        }

        public void changePin(String oldPin, String newPin) throws InvalidPinException {
            validatePin(oldPin);
            this.pin = newPin;
            System.out.println("PIN changed successfully.");
        }

        public void resetDailyLimit() {
            this.dailyWithdrawn = 0.0;
        }
    }

    private static void displayMenu() {
        System.out.println("\n=== ATM Menu ===");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Change PIN");
        System.out.println("5. Exit");
        System.out.print("Choose option: ");
    }

    public static void main(String[] args) {
        Account account = new Account(5000.0, "1234", 10000.0);
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter PIN: ");
        String pin = scanner.nextLine();

        try {
            account.validatePin(pin);
            boolean running = true;

            while (running) {
                displayMenu();
                String choice = scanner.nextLine();

                try {
                    switch (choice) {
                        case "1":
                            System.out.println("Balance: " + account.getBalance());
                            break;
                        case "2":
                            System.out.print("Enter amount: ");
                            double depositAmount = Double.parseDouble(scanner.nextLine());
                            account.deposit(depositAmount);
                            System.out.println("Deposited: " + depositAmount);
                            break;
                        case "3":
                            System.out.print("Enter amount: ");
                            double withdrawAmount = Double.parseDouble(scanner.nextLine());
                            account.withdraw(withdrawAmount);
                            System.out.println("Withdrawn: " + withdrawAmount);
                            break;
                        case "4":
                            System.out.print("Enter old PIN: ");
                            String oldPin = scanner.nextLine();
                            System.out.print("Enter new PIN: ");
                            String newPin = scanner.nextLine();
                            account.changePin(oldPin, newPin);
                            break;
                        case "5":
                            running = false;
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                } catch (InsufficientFundsException e) {
                    System.out.println("Error: " + e.getMessage());
                } catch (TransactionLimitException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        } catch (InvalidPinException e) {
            System.out.println("Authentication failed: " + e.getMessage());
        } finally {
            scanner.close();
        }
    }
}
