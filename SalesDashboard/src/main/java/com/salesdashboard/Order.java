package com.salesdashboard;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private LocalDate orderDate;
    private List<Item> items;

    public Order(LocalDate orderDate, List<Item> items) {
        this.orderDate = orderDate;
        this.items = items;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public List<Item> getItems() {
        return items;
    }
}