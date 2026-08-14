import java.util.Arrays;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.stream.Collectors;

public class _CSVSummaryStatistics {
    static class SalesRecord {
        String region;
        String product;
        double amount;
        SalesRecord(String region, String product, double amount) {
            this.region = region;
            this.product = product;
            this.amount = amount;
        }
        public String toString() { return region + " | " + product + " | \u20B9" + amount; }
    }
    public static void main(String[] args) {
        List<SalesRecord> records = Arrays.asList(
            new SalesRecord("North", "Laptop", 85000),
            new SalesRecord("South", "Phone", 45000),
            new SalesRecord("East", "Laptop", 92000),
            new SalesRecord("West", "Tablet", 30000),
            new SalesRecord("North", "Phone", 48000),
            new SalesRecord("South", "Laptop", 87000),
            new SalesRecord("East", "Tablet", 28000),
            new SalesRecord("West", "Phone", 42000),
            new SalesRecord("North", "Tablet", 31000),
            new SalesRecord("South", "Phone", 46000)
        );
        DoubleSummaryStatistics stats = records.parallelStream()
            .mapToDouble(r -> r.amount)
            .summaryStatistics();
        System.out.println("CSV Summary Statistics:");
        System.out.println("Count: " + stats.getCount());
        System.out.println("Sum: \u20B9" + stats.getSum());
        System.out.println("Average: \u20B9" + String.format("%.2f", stats.getAverage()));
        System.out.println("Min: \u20B9" + stats.getMin());
        System.out.println("Max: \u20B9" + stats.getMax());
        double variance = records.parallelStream()
            .mapToDouble(r -> r.amount)
            .map(a -> Math.pow(a - stats.getAverage(), 2))
            .average()
            .orElse(0.0);
        System.out.println("Std Dev: \u20B9" + String.format("%.2f", Math.sqrt(variance)));
    }
}
