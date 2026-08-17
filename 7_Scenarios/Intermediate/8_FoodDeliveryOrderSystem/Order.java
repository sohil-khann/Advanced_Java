import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;

public class Order {
    private final String orderId;
    private final List<MenuItem> items;
    private String couponCode;
    private double discount;

    private static final Pattern COUPON_PATTERN = Pattern.compile("^[A-Z0-9]{4,10}$");

    public Order(String orderId, String couponCode) {
        this.orderId = Objects.requireNonNull(orderId, "Order ID cannot be null");
        this.items = new ArrayList<>();
        if (couponCode != null && !couponCode.isEmpty() && !COUPON_PATTERN.matcher(couponCode).matches()) {
            throw new IllegalArgumentException("Invalid coupon code. Must be 4-10 uppercase alphanumeric characters.");
        }
        this.couponCode = couponCode;
        this.discount = 0.0;
    }

    public void addItem(MenuItem item) {
        items.add(Objects.requireNonNull(item, "MenuItem cannot be null"));
    }

    public double calculateTotal() {
        double total = items.stream().mapToDouble(MenuItem::getPrice).sum();
        return total * (1 - discount);
    }

    public String getOrderId() {
        return orderId;
    }

    public List<MenuItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public String getCouponCode() {
        return couponCode;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId='" + orderId + '\'' +
                ", items=" + items +
                ", couponCode='" + couponCode + '\'' +
                ", discount=" + discount +
                '}';
    }
}
