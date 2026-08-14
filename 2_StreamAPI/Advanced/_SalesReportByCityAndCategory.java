import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class _SalesReportByCityAndCategory {
    static class Sale {
        String city;
        String category;
        double amount;
        Sale(String city, String category, double amount) {
            this.city = city;
            this.category = category;
            this.amount = amount;
        }
        public String toString() { return city + " | " + category + " | \u20B9" + amount; }
    }
    public static void main(String[] args) {
        List<Sale> sales = Arrays.asList(
            new Sale("Mumbai", "Electronics", 45000),
            new Sale("Delhi", "Clothing", 32000),
            new Sale("Mumbai", "Clothing", 28000),
            new Sale("Bangalore", "Electronics", 52000),
            new Sale("Delhi", "Electronics", 38000),
            new Sale("Bangalore", "Clothing", 21000),
            new Sale("Mumbai", "Groceries", 15000),
            new Sale("Delhi", "Groceries", 18000)
        );
        Map<String, Map<String, List<Sale>>> report = sales.stream()
            .collect(Collectors.groupingBy(s -> s.city,
                Collectors.groupingBy(s -> s.category)));
        report.forEach((city, catMap) -> {
            System.out.println("City: " + city);
            catMap.forEach((category, saleList) -> {
                double total = saleList.stream().mapToDouble(s -> s.amount).sum();
                System.out.println("  " + category + " total: \u20B9" + total);
            });
        });
    }
}
