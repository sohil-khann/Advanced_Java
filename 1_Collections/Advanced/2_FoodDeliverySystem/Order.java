import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


/**
 * Represents a customer order containing multiple order items.
 */
public class Order implements Comparable<Order> {
    private static int nextId = 1;

    private final int orderId;
    private String customerName;
    private int loyaltyPoints; // higher = more priority
    private List<OrderItem> items;
    private LocalDateTime orderTime;
    private String status; // PENDING, PREPARING, READY, DELIVERED
    private BigDecimal totalAmount;

    /**
     * Constructs an Order with auto-generated ID.
     */
    public Order(String customerName, int loyaltyPoints) {
        this.orderId = nextId++;
        this.customerName = customerName;
        this.loyaltyPoints = loyaltyPoints;
        this.items = new ArrayList<>();
        this.orderTime = LocalDateTime.now();
        this.status = "PENDING";
        this.totalAmount = BigDecimal.ZERO;
    }

    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public int getLoyaltyPoints() { return loyaltyPoints; }
    public List<OrderItem> getItems() { return items; }
    public LocalDateTime getOrderTime() { return orderTime; }
    public String getStatus() { return status; }
    public BigDecimal getTotalAmount() { return totalAmount; }

    public void setStatus(String status) { this.status = status; }

    /**
     * Adds an item to the order and recalculates total.
     */
    public void addItem(OrderItem item) {
        items.add(item);
        totalAmount = totalAmount.add(item.getTotalPrice());
    }

    /**
     * Calculates total preparation time for all items in the order.
     */
    public int getTotalPreparationTime() {
        return items.stream()
                .mapToInt(OrderItem::getPreparationTimeMinutes)
                .sum();
    }

    /**
     * Priority ordering: higher loyalty points first, then shorter prep time.
     * Used by PriorityQueue.
     */
    @Override
    public int compareTo(Order other) {
        int loyaltyCompare = Integer.compare(other.loyaltyPoints, this.loyaltyPoints);
        if (loyaltyCompare != 0) {
            return loyaltyCompare;
        }
        return Integer.compare(this.getTotalPreparationTime(), other.getTotalPreparationTime());
    }

    @Override
    public String toString() {
        return String.format("Order#%d [%s] Customer: %s (Loyalty: %d) Items: %d Total: \u20B9%.2f PrepTime: %d min Status: %s",
                orderId, orderTime.toLocalTime(), customerName, loyaltyPoints, items.size(), totalAmount, getTotalPreparationTime(), status);
    }
}
