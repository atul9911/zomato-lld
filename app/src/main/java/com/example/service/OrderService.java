package com.example.service;

import com.example.enums.OrderStatus;
import com.example.exception.CustomException;
import com.example.model.Order;
import com.example.model.Restaurant;
import com.example.model.User;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
public class OrderService {
    RestaurantService restaurantService;
    Map<String, Order> orderMap;

    public OrderService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
        orderMap = new HashMap<>();
    }

    public Order createOrder(List<String> items, Restaurant restaurant, User user) {
        Set<String> itemsByResturant = restaurant.getMenu().getItems().keySet();

        for (String item : items) {
            if (!itemsByResturant.contains(item)) {
                throw new CustomException("Item can not be fulfilled by Resturant");
            }
        }

        int currentCapacity = restaurant.getCurrentCapacity();
        if (currentCapacity + items.size() > restaurant.getMaxCapacity()) {
            log.error("Not have Enough Capacity");
            throw new CustomException("Resturant Can not fulfill the order");
        }

        log.info("Creating Order");
        Order order = new Order(restaurant, OrderStatus.PROCESSING, items, user);
        order.getRestaurant().setCurrentCapacity(currentCapacity + items.size());
        orderMap.put(order.getId(), order);
        return order;
    }

    public Order updateOrderStatus(Order order, OrderStatus orderStatus) {
        if (order.getStatus().equals(OrderStatus.FULFILLED)) {
            throw new CustomException("Order is already fulfilled");
        }

        order.setStatus(orderStatus);

        if (orderStatus.equals(OrderStatus.FULFILLED)) {
            int currentCapacity = order.getRestaurant().getCurrentCapacity();
            order.getRestaurant().setCurrentCapacity(currentCapacity - order.getItemsList().size());
        }

        return order;
    }

    public Map<String, Order> getOrderMap() {
        return orderMap;
    }
}
