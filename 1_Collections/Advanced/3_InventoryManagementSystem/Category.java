import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a product category in the inventory.
 */
public class Category {
    private String name;
    private String description;
    private List<Product> products;

    /**
     * Constructs a Category.
     *
     * @param name        the category name
     * @param description a brief description
     */
    public Category(String name, String description) {
        this.name = name;
        this.description = description;
        this.products = new ArrayList<>();
    }

    public String getName() { return name; }
    public String getDescription() { return description; }
    public List<Product> getProducts() { return Collections.unmodifiableList(products); }

    /**
     * Adds a product to this category.
     */
    public void addProduct(Product product) {
        products.add(product);
    }

    /**
     * Removes a product from this category by product ID.
     */
    public boolean removeProduct(int productId) {
        return products.removeIf(p -> p.getProductId() == productId);
    }

    @Override
    public String toString() {
        return String.format("Category{name='%s', description='%s', products=%d}", name, description, products.size());
    }
}
