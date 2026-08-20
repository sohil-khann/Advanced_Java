package com.salesdashboard;

import java.util.Map;

public class Store {
    private String storeId;
    private String name;
    private Map<String, Customer> customers; // key is customer name

    public Store(String storeId, String name, Map<String, Customer> customers) {
        this.storeId = storeId;
        this.name = name;
        this.customers = customers;
    }

    public String getStoreId() {
        return storeId;
    }

    public String getName() {
        return name;
    }

    public Map<String, Customer> getCustomers() {
        return customers;
    }
}