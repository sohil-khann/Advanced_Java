import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class OrderSystem {
    private final List<MenuItem> menu;
    private final List<Order> orders;

    public static class InvalidOrderException extends Exception {
        public InvalidOrderException(String message) {
            super(message);
        }
    }

    public OrderSystem() {
        this.menu = new ArrayList<>();
        this.orders = new ArrayList<>();
    }

    public void addMenuItem(MenuItem item) {
        menu.add(Objects.requireNonNull(item, "MenuItem cannot be null"));
    }

    public void placeOrder(Order order) throws InvalidOrderException {
        if (order.getItems() == null || order.getItems().isEmpty()) {
            throw new InvalidOrderException("Order must contain at least one item.");
        }
        orders.add(order);
    }

    public double getTotalRevenue() {
        return orders.stream()
                .mapToDouble(Order::calculateTotal)
                .sum();
    }

    public Map<String, Long> getMostOrderedItems() {
        return orders.stream()
                .flatMap(o -> o.getItems().stream())
                .collect(Collectors.groupingBy(MenuItem::getName, Collectors.counting()));
    }

    public List<MenuItem> getMenuByCategory(String category) {
        return menu.stream()
                .filter(m -> m.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        OrderSystem system = new OrderSystem();
        system.addMenuItem(new MenuItem("M001", "Pizza", 299.0, "Main"));
        system.addMenuItem(new MenuItem("M002", "Burger", 149.0, "Main"));
        system.addMenuItem(new MenuItem("M003", "Coke", 49.0, "Drink"));
        system.addMenuItem(new MenuItem("M004", "Pasta", 249.0, "Main"));

        try {
            Order order1 = new Order("O001", "SAVE20");
            order1.addItem(system.getMenuByCategory("Main").get(0));
            order1.addItem(system.getMenuByCategory("Drink").get(0));
            order1.setDiscount(0.2);
            system.placeOrder(order1);

            Order order2 = new Order("O002", "DEAL10");
            order2.addItem(system.getMenuByCategory("Main").get(1));
            order2.addItem(system.getMenuByCategory("Drink").get(0));
            order2.setDiscount(0.1);
            system.placeOrder(order2);

            Order order3 = new Order("O003", null);
            order3.addItem(system.getMenuByCategory("Main").get(3));
            system.placeOrder(order3);
        } catch (InvalidOrderException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Total Revenue: " + system.getTotalRevenue());
        System.out.println("Most Ordered Items: " + system.getMostOrderedItems());
        System.out.println("Menu by Category 'Main': " + system.getMenuByCategory("Main"));
    }
}
