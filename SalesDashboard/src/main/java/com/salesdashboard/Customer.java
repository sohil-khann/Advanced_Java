package com.salesdashboard;

import java.util.List;
import java.util.Map;

public class Customer {
    private String name;
    private Map<String, List<Order>> orders; // key could be store ID or similar

    public Customer(String name, Map<String, List<Order>> orders) {
        this.name = name;
        this.orders = orders;
    }

    public String getName() {
        return name;
    }

    public Map<String, List<Order>> getOrders() {
        return orders;
    }

    // Helper method to get all orders as a flat list
    public List<Order> getAllOrders() {
        return orders.values().stream()
                .flatMap(List::stream)
                .toList();
    }
}