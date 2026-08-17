public class SuspiciousTransactionException extends Exception {
    public SuspiciousTransactionException(String message) {
        super(message);
    }

    public SuspiciousTransactionException(String message, Throwable cause) {
        super(message, cause);
    }
}
