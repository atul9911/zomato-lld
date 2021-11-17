package com.example.model;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Restaurant {
    String id;
    String name;
    int maxCapacity;
    Menu menu;
    String location;
    int currentCapacity;
    List<Map<Integer, String>> ratings;

    public Restaurant(String name, int maxCapacity, Menu menu, String location) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.maxCapacity = maxCapacity;
        this.menu = menu;
        this.location = location;
        this.currentCapacity = 0;
    }

    public String getName() {
        return name;
    }

    public int getMaxCapacity() {
        return maxCapacity;
    }

    public Menu getMenu() {
        return menu;
    }

    public String getLocation() {
        return location;
    }

    public String getId() {
        return id;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public void setCurrentCapacity(int currentCapacity) {
        this.currentCapacity = currentCapacity;
    }

    public List<Map<Integer, String>> getRatings() {
        return ratings;
    }

    public void addRatings(Map<Integer, String> ratings) {
        this.ratings.add(ratings);
    }
}
