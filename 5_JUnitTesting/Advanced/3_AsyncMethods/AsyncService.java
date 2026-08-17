import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class AsyncService {

    public CompletableFuture<String> processAsync(String data) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted", e);
            }
            if (data == null || data.isEmpty()) {
                throw new IllegalArgumentException("Data cannot be empty");
            }
            return data.toUpperCase();
        });
    }

    public CompletableFuture<Integer> processWithTimeout(String data, long timeoutMs) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                throw new RuntimeException("Interrupted", e);
            }
            return data.length();
        }).orTimeout(timeoutMs, TimeUnit.MILLISECONDS);
    }

    public CompletableFuture<String> processWithException(String data) {
        return CompletableFuture.supplyAsync(() -> {
            if ("error".equalsIgnoreCase(data)) {
                throw new RuntimeException("Simulated failure");
            }
            return data.toUpperCase();
        });
    }
}
