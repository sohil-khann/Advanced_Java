import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    private List<Item> items;
    private double discount;

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.discount = 0.0;
    }

    public void addItem(Item item) {
        if (item == null || item.getPrice() < 0) {
            throw new IllegalArgumentException("Invalid item");
        }
        items.add(item);
    }

    public void removeItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        items.remove(item);
    }

    public double getTotal() {
        double total = 0;
        for (Item item : items) {
            total += item.getPrice();
        }
        return total * (1 - discount);
    }

    public void applyDiscount(double discount) {
        if (discount < 0 || discount > 1) {
            throw new IllegalArgumentException("Discount must be between 0 and 1");
        }
        this.discount = discount;
    }

    public int getItemCount() {
        return items.size();
    }

    public List<Item> getItems() {
        return new ArrayList<>(items);
    }

    public static class Item {
        private String name;
        private double price;

        public Item(String name, double price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Item item = (Item) obj;
            return Double.compare(item.price, price) == 0 && (name != null ? name.equals(item.name) : item.name == null);
        }
    }
}
