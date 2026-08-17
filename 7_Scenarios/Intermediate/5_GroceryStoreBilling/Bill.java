import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;

public class Bill {
    private String billId;
    private LocalDate date;
    private Map<Product, Integer> items;
    private double totalAmount;
    private double discountPercentage;
    private double finalAmount;

    public Bill(String billId, LocalDate date, Map<Product, Integer> items, double discountPercentage) {
        this.billId = billId;
        this.date = date;
        this.items = new LinkedHashMap<>(items);
        this.discountPercentage = discountPercentage;
        this.totalAmount = calculateTotal();
        this.finalAmount = totalAmount - (totalAmount * discountPercentage / 100.0);
    }

    private double calculateTotal() {
        double sum = 0.0;
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            sum += entry.getKey().getPrice() * entry.getValue();
        }
        return sum;
    }

    public String getBillId() {
        return billId;
    }

    public LocalDate getDate() {
        return date;
    }

    public Map<Product, Integer> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void printBill() {
        System.out.println("========================================");
        System.out.println("Bill ID: " + billId);
        System.out.println("Date: " + date);
        System.out.println("----------------------------------------");
        System.out.printf("%-20s %10s %10s %12s%n", "Product", "Price", "Qty", "Subtotal");
        System.out.println("----------------------------------------");
        for (Map.Entry<Product, Integer> entry : items.entrySet()) {
            Product p = entry.getKey();
            int qty = entry.getValue();
            double subtotal = p.getPrice() * qty;
            System.out.printf("%-20s %10.2f %10d %12.2f%n", p.getName(), p.getPrice(), qty, subtotal);
        }
        System.out.println("----------------------------------------");
        System.out.printf("Total Amount: %.2f%n", totalAmount);
        System.out.printf("Discount: %.2f%%%n", discountPercentage);
        System.out.printf("Final Amount: %.2f%n", finalAmount);
        System.out.println("========================================");
    }
}