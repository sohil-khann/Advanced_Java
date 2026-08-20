package com.salesdashboard;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class SalesDashboard {

    public static void main(String[] args) {
        // Build sample data
        Map<String, List<com.salesdashboard.City>> cityToStoresMap = buildSampleData();

        // Current date for filtering last 60 days
        LocalDate cutoffDate = LocalDate.now().minusDays(60);

        // 1. Traverse the entire cityToStoresMap to process customer orders from all stores in all cities
        // 2. Filter customers who placed at least 3 orders in the last 60 days
        // 3. Map each qualified customer to their full list of purchased items
        // 4. Use flatMap() to flatten all item lists into a single stream of items across all cities and stores
        // 5. Use distinct() to eliminate duplicate items (based on item name)
        // 6. Sort the items in descending order of price
        // 7. Use peek() to log each item's name and price as it flows through the stream
        // 8. Skip the top 2 most expensive items and limit to the next 10 items to show "Top Affordable Picks"
        // 9. Group the final items into a Map<String, List<Item>> using collect() where the key is the category
        // 10. Count the number of items in the "Electronics" category
        // 11. Use anyMatch() to check if any item in the top affordable picks has a price over $500
        // 12. Use allMatch() to ensure that all items in the selection are priced above $10
        // 13. Use noneMatch() to verify that none of the item names are empty or null
        // 14. Use findFirst() to retrieve the first item in the "Home Appliances" category, if any
        // 15. Use findAny() to retrieve any item in the "Fitness" category
        // 16. Use reduce() to calculate the total value of all selected items (sum of prices)

        List<Item> topAffordablePicks = cityToStoresMap.values().stream()
                .flatMap(List::stream) // Stream<City>
                .flatMap(city -> city.getStores().stream()) // Stream<Store>
                .flatMap(store -> store.getCustomers().values().stream()) // Stream<Customer>
                .filter(customer -> customer.getAllOrders().stream()
                        .filter(order -> !order.getOrderDate().isBefore(cutoffDate))
                        .count() >= 3) // Filter customers with at least 3 orders in last 60 days
                .flatMap(customer -> customer.getAllOrders().stream()) // Stream<Order>
                .flatMap(order -> order.getItems().stream()) // Stream<Item>
                .distinct() // Eliminate duplicates based on item name (equals/hashCode)
                .sorted(Comparator.comparingDouble(com.salesdashboard.Item::getPrice).reversed()) // Descending price
                .peek(item -> System.out.println("Processing: " + item.getName() + " - $" + item.getPrice()))
                .skip(2) // Skip top 2 most expensive
                .limit(10) // Take next 10
                .toList();

        System.out.println("\n=== Top Affordable Picks ===");
        topAffordablePicks.forEach(item -> System.out.println(item.getName() + " - $" + item.getPrice() + " [" + item.getCategory() + "]"));

        // 9. Group by category
        Map<String, List<com.salesdashboard.Item>> itemsByCategory = topAffordablePicks.stream()
                .collect(Collectors.groupingBy(com.salesdashboard.Item::getCategory));

        System.out.println("\n=== Items Grouped by Category ===");
        itemsByCategory.forEach((category, items) -> {
            System.out.println(category + ":");
            items.forEach(item -> System.out.println("  " + item.getName() + " - $" + item.getPrice()));
        });

        // 10. Count items in "Electronics" category
        long electronicsCount = itemsByCategory.getOrDefault("Electronics", List.of()).size();
        System.out.println("\n=== Electronics Category Count: " + electronicsCount + " ===");

        // 11. anyMatch - check if any item price > $500
        boolean anyOver500 = topAffordablePicks.stream()
                .anyMatch(item -> item.getPrice() > 500);
        System.out.println("=== Any item over $500: " + anyOver500 + " ===");

        // 12. allMatch - ensure all items priced above $10
        boolean allAbove10 = topAffordablePicks.stream()
                .allMatch(item -> item.getPrice() > 10);
        System.out.println("=== All items above $10: " + allAbove10 + " ===");

        // 13. noneMatch - verify no item names are empty or null
        boolean noneEmptyNames = topAffordablePicks.stream()
                .noneMatch(item -> item.getName() == null || item.getName().trim().isEmpty());
        System.out.println("=== No empty/null names: " + noneEmptyNames + " ===");

        // 14. findFirst - first item in "Home Appliances" category
        Optional<Item> firstHomeAppliance = topAffordablePicks.stream()
                .filter(item -> "Home Appliances".equals(item.getCategory()))
                .findFirst();
        System.out.println("=== First Home Appliance: " + firstHomeAppliance.map(Item::getName).orElse("None") + " ===");

        // 15. findAny - any item in "Fitness" category
        Optional<Item> anyFitness = topAffordablePicks.stream()
                .filter(item -> "Fitness".equals(item.getCategory()))
                .findAny();
        System.out.println("=== Any Fitness Item: " + anyFitness.map(Item::getName).orElse("None") + " ===");

        // 16. reduce - total value of all selected items
        double totalValue = topAffordablePicks.stream()
                .map(Item::getPrice)
                .reduce(0.0, Double::sum);
        System.out.println("=== Total Value of Top Affordable Picks: $" + totalValue + " ===");
    }

    private static Map<String, List<City>> buildSampleData() {
        Map<String, List<City>> cityToStoresMap = new HashMap<>();

        // Create items
        Item laptop = new Item("Gaming Laptop", 1200.0, "Electronics");
        Item phone = new Item("Smartphone", 800.0, "Electronics");
        Item tablet = new Item("Tablet", 450.0, "Electronics");
        Item headphones = new Item("Wireless Headphones", 200.0, "Electronics");
        Item smartwatch = new Item("Smartwatch", 350.0, "Electronics");

        Item fridge = new Item("Refrigerator", 1500.0, "Home Appliances");
        Item washer = new Item("Washing Machine", 900.0, "Home Appliances");
        Item microwave = new Item("Microwave", 250.0, "Home Appliances");
        Item blender = new Item("Blender", 80.0, "Home Appliances");
        Item vacuum = new Item("Robot Vacuum", 400.0, "Home Appliances");

        Item treadmill = new Item("Treadmill", 800.0, "Fitness");
        Item dumbbells = new Item("Dumbbell Set", 150.0, "Fitness");
        Item yogaMat = new Item("Yoga Mat", 30.0, "Fitness");
        Item resistanceBands = new Item("Resistance Bands", 25.0, "Fitness");
        Item kettlebell = new Item("Kettlebell", 60.0, "Fitness");

        Item shirt = new Item("T-Shirt", 25.0, "Clothing");
        Item jeans = new Item("Jeans", 60.0, "Clothing");
        Item jacket = new Item("Winter Jacket", 120.0, "Clothing");
        Item sneakers = new Item("Running Shoes", 110.0, "Clothing");
        Item hat = new Item("Baseball Cap", 20.0, "Clothing");

        Item book1 = new Item("Java Programming", 45.0, "Books");
        Item book2 = new Item("Clean Code", 40.0, "Books");
        Item book3 = new Item("Design Patterns", 50.0, "Books");

        LocalDate now = LocalDate.now();
        LocalDate recent1 = now.minusDays(10);
        LocalDate recent2 = now.minusDays(20);
        LocalDate recent3 = now.minusDays(30);
        LocalDate recent4 = now.minusDays(40);
        LocalDate recent5 = now.minusDays(50);
        LocalDate old = now.minusDays(100);

        // Customer 1 - 4 recent orders (qualifies)
        Customer customer1 = new Customer("Sohil", Map.of(
                "store1", List.of(
                        new Order(recent1, List.of(laptop, headphones)),
                        new Order(recent2, List.of(phone, smartwatch)),
                        new Order(recent3, List.of(tablet)),
                        new Order(recent4, List.of(fridge))
                )
        ));

        // Customer 2 - 3 recent orders (qualifies)
        Customer customer2 = new Customer("Tanuj", Map.of(
                "store1", List.of(
                        new Order(recent1, List.of(washer, microwave)),
                        new Order(recent2, List.of(blender, vacuum)),
                        new Order(recent3, List.of(treadmill, dumbbells))
                )
        ));

        // Customer 3 - 5 recent orders (qualifies)
        Customer customer3 = new Customer("Raj", Map.of("store2", List.of(
                        new Order(recent1, List.of(yogaMat, resistanceBands)),
                        new Order(recent2, List.of(kettlebell, shirt)),
                        new Order(recent3, List.of(jeans, jacket)),
                        new Order(recent4, List.of(sneakers, hat)),
                        new Order(recent5, List.of(book1, book2))
                )
        ));

        // Customer 4 - only 2 recent orders (does NOT qualify)
        Customer customer4 = new Customer("Faiz", Map.of(
                "store2", List.of(
                        new Order(recent1, List.of(book3)),
                        new Order(recent2, List.of(laptop))
                )
        ));

        // Customer 5 - 3 recent orders (qualifies)
        Customer customer5 = new Customer("Junaid", Map.of(
                "store3", List.of(
                        new Order(recent1, List.of(fridge, washer)),
                        new Order(recent2, List.of(treadmill)),
                        new Order(recent3, List.of(phone, tablet))
                )
        ));

        // Create stores
        Store store1 = new Store("store1", "Downtown Store", Map.of(
                "Sohil", customer1,
                "Tanuj", customer2
        ));

        com.salesdashboard.Store store2 = new Store("store2", "Mall Store", Map.of(
                "Raj", customer3,
                "Faiz", customer4
        ));

        Store store3 = new Store("store3", "Suburban Store", Map.of(
                "Junaid", customer5
        ));

        // Create cities
        City newYork = new City("New York", List.of(store1, store2));
        City losAngeles = new City("Los Angeles", List.of(store3));

        cityToStoresMap.put("New York", List.of(newYork));
        cityToStoresMap.put("Los Angeles", List.of(losAngeles));

        return cityToStoresMap;
    }
}