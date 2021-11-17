package com.example.model;

import com.example.enums.OrderStatus;

import java.util.List;
import java.util.UUID;

public class Order {
    String id;
    Restaurant restaurant;
    OrderStatus status;
    List<String> itemsList;
    User user;

    public Order(Restaurant restaurant, OrderStatus status, List<String> itemList, User user) {
        this.id = UUID.randomUUID().toString();
        this.restaurant = restaurant;
        this.status = status;
        this.itemsList = itemList;
        this.user = user;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public List<String> getItemsList() {
        return itemsList;
    }

    public String getId() {
        return id;
    }
}
