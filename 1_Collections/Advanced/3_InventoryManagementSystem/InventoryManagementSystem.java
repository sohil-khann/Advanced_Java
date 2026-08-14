import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Inventory Management System with nested collections and custom sorting.
 */
public class InventoryManagementSystem {
    private Map<String, Category> categories;
    private List<Product> allProducts;

    public InventoryManagementSystem() {
        this.categories = new HashMap<>();
        this.allProducts = new ArrayList<>();
    }

    /**
     * Adds a new category to the system.
     */
    public void addCategory(String name, String description) {
        if (!categories.containsKey(name)) {
            categories.put(name, new Category(name, description));
        }
    }

    /**
     * Adds a product to the specified category.
     */
    public void addProduct(String categoryName, String productName, String description,
                           BigDecimal price, int stockQuantity) {
        Category category = categories.get(categoryName);
        if (category == null) {
            System.out.println("Category '" + categoryName + "' not found. Creating it.");
            addCategory(categoryName, "Auto-created category");
            category = categories.get(categoryName);
        }

        Product product = new Product(productName, description, price, stockQuantity, category);
        category.addProduct(product);
        allProducts.add(product);
        System.out.println("Product added: " + productName);
    }

    /**
     * Searches for products by name (case-insensitive partial match).
     */
    public List<Product> searchProducts(String query) {
        String lowerQuery = query.toLowerCase();
        return allProducts.stream()
                .filter(p -> p.getName().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toList());
    }

    /**
     * Returns all products sorted by price (ascending).
     */
    public List<Product> sortByPrice() {
        List<Product> sorted = new ArrayList<>(allProducts);
        sorted.sort(Comparator.comparing(Product::getPrice));
        return sorted;
    }

    /**
     * Returns all products sorted by name (using natural ordering).
     */
    public List<Product> sortByName() {
        List<Product> sorted = new ArrayList<>(allProducts);
        Collections.sort(sorted);
        return sorted;
    }

    /**
     * Groups products by category.
     */
    public Map<String, List<Product>> groupByCategory() {
        return allProducts.stream()
                .collect(Collectors.groupingBy(p -> p.getCategory().getName()));
    }

    /**
     * Updates stock quantity for a product.
     */
    public boolean updateStock(int productId, int newStock) {
        for (Product p : allProducts) {
            if (p.getProductId() == productId) {
                p.setStockQuantity(newStock);
                System.out.println("Stock updated for " + p.getName() + ": " + newStock);
                return true;
            }
        }
        System.out.println("Product ID " + productId + " not found.");
        return false;
    }

    /**
     * Displays all categories and their products.
     */
    public void displayInventory() {
        System.out.println("\n=== Inventory by Category ===");
        for (Category category : categories.values()) {
            System.out.println("\n" + category);
            for (Product product : category.getProducts()) {
                System.out.println("  " + product);
            }
        }
    }

    /**
     * Demonstrates the Inventory Management System.
     */
    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();

        // Add categories
        ims.addCategory("Electronics", "Gadgets and devices");
        ims.addCategory("Groceries", "Food and daily essentials");
        ims.addCategory("Clothing", "Apparel and accessories");

        // Add products
        ims.addProduct("Electronics", "Laptop", "15-inch gaming laptop", new BigDecimal("12999"), 10);
        ims.addProduct("Electronics", "Smartphone", "Latest model smartphone", new BigDecimal("7999"), 25);
        ims.addProduct("Electronics", "Headphones", "Noise-cancelling headphones", new BigDecimal("1999"), 50);

        ims.addProduct("Groceries", "Rice", "5kg bag of rice", new BigDecimal("120"), 100);
        ims.addProduct("Groceries", "Milk", "1 litre milk", new BigDecimal("40"), 30);
        ims.addProduct("Groceries", "Bread", "Whole wheat bread", new BigDecimal("25"), 40);

        ims.addProduct("Clothing", "T-Shirt", "Cotton t-shirt", new BigDecimal("199"), 75);
        ims.addProduct("Clothing", "Jeans", "Denim jeans", new BigDecimal("499"), 30);
        ims.addProduct("Clothing", "Jacket", "Winter jacket", new BigDecimal("899"), 15);

        // Display inventory
        ims.displayInventory();

        // Search
        System.out.println("\n=== Search for 'lap' ===");
        for (Product p : ims.searchProducts("lap")) {
            System.out.println(p);
        }

        // Sort by price
        System.out.println("\n=== Products Sorted by Price ===");
        for (Product p : ims.sortByPrice()) {
            System.out.println(String.format("%s - \u20B9%.2f", p.getName(), p.getPrice()));
        }

        // Sort by name
        System.out.println("\n=== Products Sorted by Name ===");
        for (Product p : ims.sortByName()) {
            System.out.println(p.getName());
        }

        // Group by category
        System.out.println("\n=== Products Grouped by Category ===");
        Map<String, List<Product>> grouped = ims.groupByCategory();
        for (Map.Entry<String, List<Product>> entry : grouped.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue().size() + " products");
        }

        // Update stock
        System.out.println("\n=== Updating Stock ===");
        ims.updateStock(1, 5);
    }
}
