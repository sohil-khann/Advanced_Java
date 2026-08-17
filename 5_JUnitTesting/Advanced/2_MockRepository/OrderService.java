import java.util.Optional;

public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order getOrder(int id) {
        Optional<Order> order = orderRepository.findById(id);
        return order.orElse(null);
    }

    public Order placeOrder(String productName, double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        Order order = new Order(0, productName, amount);
        return orderRepository.save(order);
    }

    public boolean cancelOrder(int id) {
        return orderRepository.deleteById(id);
    }
}
