import java.util.Objects;

public class MenuItem {
    private final String itemId;
    private final String name;
    private final double price;
    private final String category;

    public MenuItem(String itemId, String name, double price, String category) {
        this.itemId = Objects.requireNonNull(itemId, "Item ID cannot be null");
        this.name = Objects.requireNonNull(name, "Item name cannot be null");
        this.price = price;
        this.category = Objects.requireNonNull(category, "Category cannot be null");
    }

    public String getItemId() {
        return itemId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "itemId='" + itemId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                '}';
    }
}
