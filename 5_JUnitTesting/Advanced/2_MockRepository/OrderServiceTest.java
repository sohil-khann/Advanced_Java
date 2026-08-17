import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    @Test
    void testGetOrder() {
        OrderRepository repository = Mockito.mock(OrderRepository.class);
        Order order = new Order(1, "Laptop", 999.99);
        Mockito.when(repository.findById(1)).thenReturn(java.util.Optional.of(order));

        OrderService service = new OrderService(repository);
        Order found = service.getOrder(1);

        assertNotNull(found);
        assertEquals("Laptop", found.getProductName());
        Mockito.verify(repository).findById(1);
    }

    @Test
    void testGetNonExistentOrder() {
        OrderRepository repository = Mockito.mock(OrderRepository.class);
        Mockito.when(repository.findById(999)).thenReturn(java.util.Optional.empty());

        OrderService service = new OrderService(repository);
        Order found = service.getOrder(999);

        assertNull(found);
    }

    @Test
    void testPlaceOrder() {
        OrderRepository repository = Mockito.mock(OrderRepository.class);
        Order savedOrder = new Order(1, "Phone", 499.99);
        Mockito.when(repository.save(any(Order.class))).thenReturn(savedOrder);

        OrderService service = new OrderService(repository);
        Order placed = service.placeOrder("Phone", 499.99);

        assertNotNull(placed);
        assertEquals("Phone", placed.getProductName());
        Mockito.verify(repository).save(any(Order.class));
    }

    @Test
    void testPlaceOrderWithZeroAmount() {
        OrderRepository repository = Mockito.mock(OrderRepository.class);
        OrderService service = new OrderService(repository);
        assertThrows(IllegalArgumentException.class, () -> service.placeOrder("Item", 0));
    }

    @Test
    void testCancelOrder() {
        OrderRepository repository = Mockito.mock(OrderRepository.class);
        Mockito.when(repository.deleteById(1)).thenReturn(true);

        OrderService service = new OrderService(repository);
        boolean cancelled = service.cancelOrder(1);

        assertTrue(cancelled);
        Mockito.verify(repository).deleteById(1);
    }
}
