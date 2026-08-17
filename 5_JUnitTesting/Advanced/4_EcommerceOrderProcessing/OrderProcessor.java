import java.util.HashMap;
import java.util.Map;

public class OrderProcessor {

    private Map<Integer, Integer> inventory;
    private PaymentService paymentService;

    public OrderProcessor(PaymentService paymentService) {
        this.paymentService = paymentService;
        this.inventory = new HashMap<>();
    }

    public void addInventory(int productId, int quantity) {
        inventory.put(productId, quantity);
    }

    public OrderResult processOrder(Order order) {
        if (order == null) {
            return new OrderResult(false, "Order cannot be null");
        }
        if (order.getQuantity() <= 0) {
            return new OrderResult(false, "Invalid quantity");
        }
        Integer stock = inventory.getOrDefault(order.getProductId(), 0);
        if (stock < order.getQuantity()) {
            return new OrderResult(false, "Out of stock");
        }
        boolean paymentSuccess = paymentService.processPayment(order.getAmount());
        if (!paymentSuccess) {
            return new OrderResult(false, "Payment failed");
        }
        inventory.put(order.getProductId(), stock - order.getQuantity());
        return new OrderResult(true, "Order processed successfully");
    }

    public static class Order {
        private int productId;
        private int quantity;
        private double amount;

        public Order(int productId, int quantity, double amount) {
            this.productId = productId;
            this.quantity = quantity;
            this.amount = amount;
        }

        public int getProductId() {
            return productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getAmount() {
            return amount;
        }
    }

    public static class OrderResult {
        private boolean success;
        private String message;

        public OrderResult(boolean success, String message) {
            this.success = success;
            this.message = message;
        }

        public boolean isSuccess() {
            return success;
        }

        public String getMessage() {
            return message;
        }
    }
}
