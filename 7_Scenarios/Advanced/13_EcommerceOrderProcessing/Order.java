import java.time.LocalDateTime;
import java.util.*;

public class Order {
    public enum Status { PENDING, PROCESSING, COMPLETED, CANCELLED, PAYMENT_FAILED }

    private static int idGen = 1;
    private final int orderId;
    private final String customerName;
    private final List<Item> items;
    private final LocalDateTime orderDate;
    private Status status;
    private String discountCode;
    private double discountAmount;

    public Order(String customerName) {
        this.orderId = idGen++;
        this.customerName = customerName;
        this.items = new ArrayList<>();
        this.orderDate = LocalDateTime.now();
        this.status = Status.PENDING;
    }

    public int getOrderId() { return orderId; }
    public String getCustomerName() { return customerName; }
    public List<Item> getItems() { return items; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
    public String getDiscountCode() { return discountCode; }
    public double getDiscountAmount() { return discountAmount; }

    public void addItem(String name, double price, int qty) {
        items.add(new Item(name, price, qty));
    }

    public double getTotal() {
        return items.stream().mapToDouble(i -> i.price * i.quantity).sum();
    }

    public double getFinalTotal() {
        return Math.max(0, getTotal() - discountAmount);
    }

    public void applyDiscount(String code, double amount) {
        this.discountCode = code;
        this.discountAmount = amount;
    }

    public static class Item {
        public final String name;
        public final double price;
        public final int quantity;

        public Item(String name, double price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }
    }
}
