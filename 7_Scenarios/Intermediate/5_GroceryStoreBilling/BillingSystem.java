import java.util.*;
import java.util.stream.Collectors;

public class BillingSystem {
    private Map<String, Product> inventory;

    public BillingSystem() {
        this.inventory = new HashMap<>();
    }

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    public Product getProduct(String productId) {
        return inventory.get(productId);
    }

    public Bill generateBill(String billId, Map<Product, Integer> items, double discountPercentage) throws OutOfStockException {
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product product = entry.getKey();
            int requestedQty = entry.getValue();
            Product stockProduct = inventory.get(product.getProductId());
            if (stockProduct == null || stockProduct.getQuantityInStock() < requestedQty) {
                throw new OutOfStockException("Product " + product.getName() + " is out of stock or insufficient quantity.");
            }
        }

        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product stockProduct = inventory.get(entry.getKey().getProductId());
            stockProduct.setQuantityInStock(stockProduct.getQuantityInStock() - entry.getValue());
            stockProduct.incrementUnitsSold(entry.getValue());
        }

        return new Bill(billId, java.time.LocalDate.now(), items, discountPercentage);
    }

    public List<Product> findTopSellingProducts(int n) {
        return inventory.values().stream()
                .sorted(Comparator.comparingInt(Product::getUnitsSold).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    public List<Product> findTopSellingProductsByRevenue(int n) {
        return inventory.values().stream()
                .sorted(Comparator.comparingDouble((Product p) -> p.getPrice() * p.getUnitsSold()).reversed())
                .limit(n)
                .collect(Collectors.toList());
    }

    public static class OutOfStockException extends Exception {
        public OutOfStockException(String message) {
            super(message);
        }
    }

    public static void main(String[] args) {
        BillingSystem system = new BillingSystem();

        system.addProduct(new Product("P001", "Apple", 50.0, 100));
        system.addProduct(new Product("P002", "Banana", 30.0, 200));
        system.addProduct(new Product("P003", "Milk", 60.0, 50));
        system.addProduct(new Product("P004", "Bread", 40.0, 80));
        system.addProduct(new Product("P005", "Eggs", 5.0, 300));

        Map<Product, Integer> items1 = new LinkedHashMap<>();
        Product apple = system.getProduct("P001");
        Product banana = system.getProduct("P002");
        Product milk = system.getProduct("P003");
        items1.put(apple, 5);
        items1.put(banana, 10);
        items1.put(milk, 2);

        try {
            Bill bill1 = system.generateBill("B001", items1, 10.0);
            bill1.printBill();

            Map<Product, Integer> items2 = new LinkedHashMap<>();
            Product bread = system.getProduct("P004");
            Product eggs = system.getProduct("P005");
            items2.put(bread, 3);
            items2.put(eggs, 12);
            Bill bill2 = system.generateBill("B002", items2, 5.0);
            bill2.printBill();

            System.out.println("Top 3 Selling Products by Units:");
            system.findTopSellingProducts(3).forEach(p ->
                    System.out.println(p.getName() + " - Units Sold: " + p.getUnitsSold()));

            System.out.println("\nTop 3 Selling Products by Revenue:");
            system.findTopSellingProductsByRevenue(3).forEach(p ->
                    System.out.println(p.getName() + " - Revenue: " + (p.getPrice() * p.getUnitsSold())));

            System.out.println("\nRemaining Stock:");
            system.inventory.values().forEach(p ->
                    System.out.println(p.getName() + ": " + p.getQuantityInStock()));

        } catch (OutOfStockException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}