import java.time.LocalDateTime;

public class Payment {
    public enum Status { SUCCESS, FAILED, PENDING }

    private static int idGen = 1;
    private final int paymentId;
    private final int orderId;
    private final double amount;
    private final String method;
    private Status status;
    private final LocalDateTime paymentTime;
    private String failureReason;

    public Payment(int orderId, double amount, String method) {
        this.paymentId = idGen++;
        this.orderId = orderId;
        this.amount = amount;
        this.method = method;
        this.status = Status.PENDING;
        this.paymentTime = LocalDateTime.now();
    }

    public int getPaymentId() { return paymentId; }
    public int getOrderId() { return orderId; }
    public double getAmount() { return amount; }
    public String getMethod() { return method; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public LocalDateTime getPaymentTime() { return paymentTime; }
    public String getFailureReason() { return failureReason; }
    public void setFailureReason(String reason) { this.failureReason = reason; }
}
