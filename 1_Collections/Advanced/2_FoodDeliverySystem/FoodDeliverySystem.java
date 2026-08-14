import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;
public class FoodDeliverySystem {
    private PriorityQueue<Order> orderQueue;
    private List<Order> processedOrders;
    private Scanner scanner;

    public FoodDeliverySystem() {
        this.orderQueue = new PriorityQueue<>();
        this.processedOrders = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }


    public void run() {
        System.out.println("=== Food Delivery System ===");

        boolean running = true;
        while (running) {
            System.out.println("\n1. Place Order");
            System.out.println("2. Process Next Order");
            System.out.println("3. View Pending Orders");
            System.out.println("4. View Processed Orders");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    placeOrder();
                    break;
                case "2":
                    processNextOrder();
                    break;
                case "3":
                    viewPendingOrders();
                    break;
                case "4":
                    viewProcessedOrders();
                    break;
                case "5":
                    running = false;
                    System.out.println("Exiting system.");
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    private void placeOrder() {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();

        System.out.print("Enter customer loyalty points: ");
        int loyalty = Integer.parseInt(scanner.nextLine());

        Order order = new Order(name, loyalty);

        System.out.print("Enter number of items: ");
        int itemCount = Integer.parseInt(scanner.nextLine());

        for (int i = 0; i < itemCount; i++) {
            System.out.println("Item " + (i + 1) + ":");
            System.out.print("  Name: ");
            String itemName = scanner.nextLine();

            System.out.print("  Quantity: ");
            int qty = Integer.parseInt(scanner.nextLine());

            System.out.print("  Price per unit: ");
            BigDecimal price = new BigDecimal(scanner.nextLine());

            System.out.print("  Preparation time (minutes): ");
            int prepTime = Integer.parseInt(scanner.nextLine());

            order.addItem(new OrderItem(itemName, qty, price, prepTime));
        }

        orderQueue.offer(order);
        System.out.println("Order placed successfully! Order ID: " + order.getOrderId());
    }

    private void processNextOrder() {
        Order order = orderQueue.poll();
        if (order == null) {
            System.out.println("No pending orders to process.");
            return;
        }

        order.setStatus("PREPARING");
        System.out.println("Processing: " + order);

        order.setStatus("READY");
        System.out.println("Order is ready for delivery!");

        processedOrders.add(order);
    }

    private void viewPendingOrders() {
        if (orderQueue.isEmpty()) {
            System.out.println("No pending orders.");
            return;
        }

        System.out.println("\n--- Pending Orders (by priority) ---");
        // Create a temporary queue to display without modifying original
        PriorityQueue<Order> tempQueue = new PriorityQueue<>(orderQueue);
        int rank = 1;
        while (!tempQueue.isEmpty()) {
            System.out.println(rank++ + ". " + tempQueue.poll());
        }
    }

    private void viewProcessedOrders() {
        if (processedOrders.isEmpty()) {
            System.out.println("No processed orders.");
            return;
        }

        System.out.println("\n--- Processed Orders ---");
        for (int i = 0; i < processedOrders.size(); i++) {
            System.out.println((i + 1) + ". " + processedOrders.get(i));
        }
    }


    public static void main(String[] args) {
        FoodDeliverySystem system = new FoodDeliverySystem();

        // Add sample orders for demonstration
        Order order1 = new Order("Priya", 150);
        order1.addItem(new OrderItem("Pizza", 2, new BigDecimal("12.99"), 20));
        order1.addItem(new OrderItem("Salad", 1, new BigDecimal("8.50"), 10));
        system.orderQueue.offer(order1);

        Order order2 = new Order("Rahul", 50);
        order2.addItem(new OrderItem("Burger", 1, new BigDecimal("9.99"), 15));
        order2.addItem(new OrderItem("Fries", 2, new BigDecimal("4.50"), 8));
        system.orderQueue.offer(order2);

        Order order3 = new Order("Arjun", 200);
        order3.addItem(new OrderItem("Steak", 1, new BigDecimal("24.99"), 30));
        order3.addItem(new OrderItem("Soup", 1, new BigDecimal("6.00"), 12));
        system.orderQueue.offer(order3);

        System.out.println("=== Sample Orders Added ===");
        system.viewPendingOrders();

        System.out.println("\n=== Processing Orders ===");
        system.processNextOrder();
        system.processNextOrder();
        system.processNextOrder();

        System.out.println("\n=== Final Status ===");
        system.viewProcessedOrders();
        system.viewPendingOrders();
    }
}
