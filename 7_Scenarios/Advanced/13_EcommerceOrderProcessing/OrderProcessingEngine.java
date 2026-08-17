import java.time.LocalDateTime;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OrderProcessingEngine {
    private static final List<Order> orders = new ArrayList<>();
    private static final List<Payment> payments = new ArrayList<>();
    private static final Pattern DISCOUNT_PATTERN = Pattern.compile("^[A-Z]{3}\\d{3}$");

    public static void main(String[] args) {
        Order o1 = new Order("Priya");
        o1.addItem("Laptop", 50000, 1);
        o1.addItem("Mouse", 500, 2);

        Order o2 = new Order("Rahul");
        o2.addItem("Phone", 30000, 1);

        Order o3 = new Order("Priya");
        o3.addItem("Keyboard", 1000, 1);

        orders.addAll(Arrays.asList(o1, o2, o3));

        processOrder(o1);
        processOrder(o2);
        processOrder(o3);

        generateSalesReport();
        System.out.println("Top customers: " + getTopCustomers(2));
        System.out.println("Pending orders: " + countPendingOrders());
    }

    public static void processOrder(Order order) {
        if (order.getItems().isEmpty()) {
            order.setStatus(Order.Status.CANCELLED);
            return;
        }

        order.setStatus(Order.Status.PROCESSING);
        Payment payment = new Payment(order.getOrderId(), order.getTotal(), "CreditCard");
        payments.add(payment);

        if (Math.random() > 0.3) {
            payment.setStatus(Payment.Status.SUCCESS);
            order.setStatus(Order.Status.COMPLETED);
            System.out.println("Order " + order.getOrderId() + " completed for " + order.getCustomerName());
        } else {
            payment.setStatus(Payment.Status.FAILED);
            payment.setFailureReason("Insufficient funds");
            order.setStatus(Order.Status.PAYMENT_FAILED);
            System.out.println("Payment failed for order " + order.getOrderId());
        }
    }

    public static boolean validateDiscount(String code) {
        return DISCOUNT_PATTERN.matcher(code).matches();
    }

    public static void applyDiscount(int orderId, String code, double amount) {
        if (!validateDiscount(code)) {
            System.out.println("Invalid discount code: " + code);
            return;
        }
        orders.stream().filter(o -> o.getOrderId() == orderId).findFirst()
            .ifPresent(o -> o.applyDiscount(code, amount));
    }

    public static long countPendingOrders() {
        return orders.stream().filter(o -> o.getStatus() == Order.Status.PENDING).count();
    }

    public static void generateSalesReport() {
        System.out.println("\n=== Sales Report ===");
        double total = orders.stream()
            .filter(o -> o.getStatus() == Order.Status.COMPLETED)
            .mapToDouble(Order::getFinalTotal)
            .sum();
        System.out.println("Total Sales: " + total);

        Map<String, Double> byCustomer = orders.stream()
            .filter(o -> o.getStatus() == Order.Status.COMPLETED)
            .collect(Collectors.groupingBy(Order::getCustomerName, Collectors.summingDouble(Order::getFinalTotal)));
        byCustomer.forEach((k, v) -> System.out.println(k + ": " + v));
    }

    public static List<Map.Entry<String, Long>> getTopCustomers(int n) {
        return orders.stream()
            .filter(o -> o.getStatus() == Order.Status.COMPLETED)
            .collect(Collectors.groupingBy(Order::getCustomerName, Collectors.counting()))
            .entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(n)
            .collect(Collectors.toList());
    }
}
