import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class _MonthlyRevenueTrends {
    static class Order {
        LocalDate date;
        double amount;
        Order(LocalDate date, double amount) {
            this.date = date;
            this.amount = amount;
        }
        public String toString() { return date + " | \u20B9" + amount; }
    }
    public static void main(String[] args) {
        List<Order> orders = List.of(
            new Order(LocalDate.of(2024, 1, 5), 1200),
            new Order(LocalDate.of(2024, 1, 15), 800),
            new Order(LocalDate.of(2024, 2, 3), 1500),
            new Order(LocalDate.of(2024, 2, 20), 950),
            new Order(LocalDate.of(2024, 2, 28), 600),
            new Order(LocalDate.of(2024, 3, 10), 1800),
            new Order(LocalDate.of(2024, 3, 25), 2100),
            new Order(LocalDate.of(2024, 3, 30), 700)
        );
        Map<YearMonth, Double> monthlyRevenue = orders.stream()
            .collect(Collectors.groupingBy(
                o -> YearMonth.from(o.date),
                Collectors.summingDouble(o -> o.amount)
            ));
        System.out.println("Monthly Revenue Trends:");
        monthlyRevenue.forEach((month, revenue) ->
            System.out.println(month + ": \u20B9" + revenue)
        );
    }
}
