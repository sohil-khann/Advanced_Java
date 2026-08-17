import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class OrderProcessorTest {

    @Test
    void testSuccessfulOrder() {
        PaymentService paymentService = new PaymentService();
        OrderProcessor processor = new OrderProcessor(paymentService);
        processor.addInventory(1, 10);
        OrderProcessor.Order order = new OrderProcessor.Order(1, 2, 100.0);
        OrderProcessor.OrderResult result = processor.processOrder(order);
        assertTrue(result.isSuccess());
        assertEquals("Order processed successfully", result.getMessage());
    }

    @Test
    void testPaymentFailure() {
        PaymentService paymentService = new PaymentService();
        OrderProcessor processor = new OrderProcessor(paymentService);
        processor.addInventory(1, 10);
        OrderProcessor.Order order = new OrderProcessor.Order(1, 2, 0.0);
        OrderProcessor.OrderResult result = processor.processOrder(order);
        assertFalse(result.isSuccess());
        assertEquals("Payment failed", result.getMessage());
    }

    @Test
    void testOutOfStock() {
        PaymentService paymentService = new PaymentService();
        OrderProcessor processor = new OrderProcessor(paymentService);
        processor.addInventory(1, 1);
        OrderProcessor.Order order = new OrderProcessor.Order(1, 2, 100.0);
        OrderProcessor.OrderResult result = processor.processOrder(order);
        assertFalse(result.isSuccess());
        assertEquals("Out of stock", result.getMessage());
    }

    @Test
    void testInvalidQuantity() {
        PaymentService paymentService = new PaymentService();
        OrderProcessor processor = new OrderProcessor(paymentService);
        OrderProcessor.Order order = new OrderProcessor.Order(1, 0, 100.0);
        OrderProcessor.OrderResult result = processor.processOrder(order);
        assertFalse(result.isSuccess());
        assertEquals("Invalid quantity", result.getMessage());
    }

    @Test
    void testNullOrder() {
        PaymentService paymentService = new PaymentService();
        OrderProcessor processor = new OrderProcessor(paymentService);
        OrderProcessor.OrderResult result = processor.processOrder(null);
        assertFalse(result.isSuccess());
        assertEquals("Order cannot be null", result.getMessage());
    }
}
