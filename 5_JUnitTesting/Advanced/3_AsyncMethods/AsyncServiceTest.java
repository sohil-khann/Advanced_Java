import org.junit.jupiter.api.Test;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import static org.junit.jupiter.api.Assertions.*;

class AsyncServiceTest {

    private final AsyncService asyncService = new AsyncService();

    @Test
    void testAsyncCompletion() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = asyncService.processAsync("hello");
        String result = future.get();
        assertEquals("HELLO", result);
    }

    @Test
    void testAsyncException() {
        CompletableFuture<String> future = asyncService.processAsync(null);
        ExecutionException exception = assertThrows(ExecutionException.class, future::get);
        assertTrue(exception.getCause() instanceof IllegalArgumentException);
    }

    @Test
    void testProcessWithTimeout() throws ExecutionException, InterruptedException, TimeoutException {
        CompletableFuture<Integer> future = asyncService.processWithTimeout("hello", 200);
        int result = future.get(1, TimeUnit.SECONDS);
        assertEquals(5, result);
    }

    @Test
    void testProcessTimeoutFailure() {
        CompletableFuture<Integer> future = asyncService.processWithTimeout("hello", 10);
        assertThrows(TimeoutException.class, () -> future.get(1, TimeUnit.SECONDS));
    }

    @Test
    void testProcessWithException() {
        CompletableFuture<String> future = asyncService.processWithException("error");
        ExecutionException exception = assertThrows(ExecutionException.class, future::get);
        assertTrue(exception.getCause() instanceof RuntimeException);
    }

    @Test
    void testProcessWithExceptionSuccess() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = asyncService.processWithException("success");
        String result = future.get();
        assertEquals("SUCCESS", result);
    }
}
