package com.example.model;

import java.util.Map;
import java.util.UUID;

public class Menu {
    String id;
    Map<String, Integer> items;

    public Menu(Map<String, Integer> items) {
        this.id = UUID.randomUUID().toString();
        this.items = items;
    }

    public String getId() {
        return id;
    }

    public Map<String, Integer> getItems() {
        return items;
    }
}
