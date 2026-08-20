package com.salesdashboard;

import java.util.List;

public class City {
    private String name;
    private List<Store> stores;

    public City(String name, List<Store> stores) {
        this.name = name;
        this.stores = stores;
    }

    public String getName() {
        return name;
    }

    public List<Store> getStores() {
        return stores;
    }
}