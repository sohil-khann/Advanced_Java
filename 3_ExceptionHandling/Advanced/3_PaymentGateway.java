import java.util.Random;

class NetworkException extends Exception {
    public NetworkException(String message) {
        super(message);
    }
}

class TimeoutException extends Exception {
    public TimeoutException(String message) {
        super(message);
    }
}

class PaymentGateway {
    private static final int MAX_RETRIES = 3;
    private static final Random random = new Random();

    public static boolean processPayment(double amount) throws NetworkException, TimeoutException {
        int rand = random.nextInt(4);
        if (rand == 0) {
            throw new NetworkException("Network connection lost");
        } else if (rand == 1) {
            throw new TimeoutException("Transaction timed out");
        }
        return true;
    }

    public static boolean processWithRetry(double amount) {
        int attempt = 0;
        long delay = 1000;

        while (attempt < MAX_RETRIES) {
            try {
                return processPayment(amount);
            } catch (NetworkException | TimeoutException e) {
                attempt++;
                if (attempt >= MAX_RETRIES) {
                    System.out.println("Payment failed after " + attempt + " attempts: " + e.getMessage());
                    return false;
                }
                System.out.println("Attempt " + attempt + " failed: " + e.getMessage() + ". Retrying in " + delay + "ms...");
                try {
                    Thread.sleep(delay);
                } catch (InterruptedException ie) {
                    Thread.currentThread().interrupt();
                    return false;
                }
                delay *= 2;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        double[] amounts = {100.0, 250.0, 50.0};

        for (double amount : amounts) {
            System.out.println("\nProcessing payment of " + amount);
            boolean success = processWithRetry(amount);
            if (success) {
                System.out.println("Payment of " + amount + " successful");
            } else {
                System.out.println("Payment of " + amount + " failed");
            }
        }
    }
}
