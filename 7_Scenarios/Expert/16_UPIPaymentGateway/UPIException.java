public class UPIException extends Exception {
    public enum ErrorCode { INVALID_UPI_ID, INSUFFICIENT_BALANCE, NETWORK_ERROR, PROCESSING_ERROR }

    private final ErrorCode errorCode;

    public UPIException(ErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() { return errorCode; }
}
