import java.math.BigDecimal;

/**
 * Represents a product in the inventory.
 */
public class Product implements Comparable<Product> {
    private static int nextId = 1;

    private final int productId;
    private String name;
    private String description;
    private BigDecimal price;
    private int stockQuantity;
    private Category category;

    /**
     * Constructs a Product with auto-generated ID.
     */
    public Product(String name, String description, BigDecimal price, int stockQuantity, Category category) {
        this.productId = nextId++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.category = category;
    }

    public int getProductId() { return productId; }
    public String getName() { return name; }
    public String getDescription() { return description; }
    public BigDecimal getPrice() { return price; }
    public int getStockQuantity() { return stockQuantity; }
    public Category getCategory() { return category; }

    public void setPrice(BigDecimal price) { this.price = price; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }
    public void setCategory(Category category) { this.category = category; }

    /**
     * Compares by name first, then by product ID for tie-breaking.
     */
    @Override
    public int compareTo(Product other) {
        int nameCompare = this.name.compareToIgnoreCase(other.name);
        if (nameCompare != 0) {
            return nameCompare;
        }
        return Integer.compare(this.productId, other.productId);
    }

    @Override
    public String toString() {
        return String.format("Product{id=%d, name='%s', price=\u20B9%.2f, stock=%d, category='%s'}",
                productId, name, price, stockQuantity, category != null ? category.getName() : "None");
    }
}
