import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class UPIPaymentGateway {
    private static final List<Transaction> transactions = new ArrayList<>();
    private static final Pattern UPI_PATTERN = Pattern.compile("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+$");

    public static void main(String[] args) {
        try {
            Transaction t1 = initiatePayment("priya@upi", 1500, "merchant@upi");
            Transaction t2 = initiatePayment("rahul@upi", 2500, "merchant@upi");
            Transaction t3 = initiatePayment("invalid", 500, "merchant@upi");

            processTransaction(t1);
            processTransaction(t2);
            processTransaction(t3);

            generateSettlementReport();
        } catch (UPIException e) {
            System.out.println("UPI Error: " + e.getMessage() + " (" + e.getErrorCode() + ")");
        }
    }

    public static Transaction initiatePayment(String upiId, double amount, String payee) throws UPIException {
        if (!UPI_PATTERN.matcher(upiId).matches()) {
            throw new UPIException(UPIException.ErrorCode.INVALID_UPI_ID, "Invalid UPI ID: " + upiId);
        }
        return new Transaction(upiId, amount, payee);
    }

    public static void processTransaction(Transaction txn) throws UPIException {
        try {
            simulateProcessing(txn);
            if (txn.getStatus() == Transaction.Status.SUCCESS) {
                System.out.println("Payment successful: " + txn.getTxnId());
            }
        } catch (Exception e) {
            if (txn.getRetryCount() < 3) {
                txn.incrementRetry();
                txn.setStatus(Transaction.Status.RETRYING);
                System.out.println("Retrying transaction " + txn.getTxnId() + " (attempt " + txn.getRetryCount() + ")");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException ignored) {}
                processTransaction(txn);
            } else {
                txn.setStatus(Transaction.Status.FAILED);
                System.out.println("Transaction failed after retries: " + txn.getTxnId());
            }
        }
    }

    private static void simulateProcessing(Transaction txn) throws UPIException {
        if (Math.random() < 0.4) {
            throw new UPIException(UPIException.ErrorCode.NETWORK_ERROR, "Network timeout");
        }
        if (Math.random() < 0.2) {
            throw new UPIException(UPIException.ErrorCode.INSUFFICIENT_BALANCE, "Insufficient balance");
        }
        txn.setStatus(Transaction.Status.SUCCESS);
    }

    public static void generateSettlementReport() {
        System.out.println("\n=== Settlement Report ===");
        Map<String, Double> settlement = transactions.stream()
            .filter(t -> t.getStatus() == Transaction.Status.SUCCESS)
            .collect(Collectors.groupingBy(Transaction::getUpiId, Collectors.summingDouble(Transaction::getAmount)));
        settlement.forEach((upi, amount) -> System.out.println(upi + ": " + amount));
    }
}
