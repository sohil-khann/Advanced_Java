public class Order {
    private int id;
    private String productName;
    private double amount;

    public Order(int id, String productName, double amount) {
        this.id = id;
        this.productName = productName;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public double getAmount() {
        return amount;
    }
}
