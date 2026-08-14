import java.math.BigDecimal;

public class OrderItem {
    private String name;
    private int quantity;
    private BigDecimal price; // price per unit
    private int preparationTimeMinutes; // preparation time in minutes


    public OrderItem(String name, int quantity, BigDecimal price, int preparationTimeMinutes) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.preparationTimeMinutes = preparationTimeMinutes;
    }

    public String getName() { return name; }
    public int getQuantity() { return quantity; }
    public BigDecimal getPrice() { return price; }
    public int getPreparationTimeMinutes() { return preparationTimeMinutes; }


    public BigDecimal getTotalPrice() {
        return price.multiply(BigDecimal.valueOf(quantity));
    }

    @Override
    public String toString() {
        return String.format("%s x%d @ \u20B9%.2f each (Prep: %d min)", name, quantity, price, preparationTimeMinutes);
    }
}

