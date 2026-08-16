import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class OutOfStockException extends Exception {
    public OutOfStockException(String message) {
        super(message);
    }
}

public class _ShoppingCartStockValidation {
    private static Map<String, Integer> stock = new HashMap<>();

    static {
        stock.put("Laptop", 5);
        stock.put("Phone", 10);
        stock.put("Headphone", 20);
        stock.put("Keyboard", 15);
        stock.put("Mouse", 30);
    }

    public static void addToCart(String item, int quantity) throws OutOfStockException {
        if (!stock.containsKey(item)) {
            throw new OutOfStockException("Item '" + item + "' is not available in the store.");
        }
        int available = stock.get(item);
        if (quantity <= 0) {
            throw new OutOfStockException("Quantity must be greater than zero.");
        }
        if (quantity > available) {
            throw new OutOfStockException(
                "Not enough stock for '" + item + "'. Requested: " + quantity + ", Available: " + available);
        }
        stock.put(item, available - quantity);
        System.out.println("Added " + quantity + " x " + item + " to cart.");
        System.out.println("Remaining stock for " + item + ": " + stock.get(item));
    }

    public static void showStock() {
        System.out.println("--- Current Stock ---");
        for (Map.Entry<String, Integer> entry : stock.entrySet()) {
            System.out.println("  " + entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("=== Shopping Cart with Stock Validation ===");
        showStock();

        try {
            System.out.print("Enter item name: ");
            String item = scanner.nextLine().trim();
            System.out.print("Enter desired quantity: ");
            int quantity = scanner.nextInt();
            addToCart(item, quantity);
            System.out.println("Item added to cart successfully.");
        } catch (OutOfStockException e) {
            System.out.println("Stock Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close();
            System.out.println("Shopping cart session ended.");
        }
    }
}