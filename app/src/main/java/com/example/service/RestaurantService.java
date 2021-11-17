package com.example.service;

import com.example.exception.CustomException;
import com.example.model.Menu;
import com.example.model.Restaurant;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class RestaurantService {
    private Map<String, Restaurant> restaurantMap;
    private MenuService menuService;

    public RestaurantService() {
        this.restaurantMap = new HashMap<>();
        this.menuService = new MenuService();
    }

    public Restaurant addRestaurant(String name, Map<String, Integer> items, int processingCapacity, String location) {
        Map<String, Integer> catalog = new HashMap<>();
        catalog.putAll(items);
        Menu menu = menuService.createMenu(catalog);
        Restaurant restaurant = new Restaurant(name, processingCapacity, menu, location);
        restaurantMap.put(name, restaurant); // {}
        return restaurant;
    }

    public Map<String, Restaurant> getRestaurantMap() {
        return restaurantMap;
    }

    public Restaurant getRestaurantByName(String name) {
        return restaurantMap.get(name);
    }

    public Restaurant addOrUpdateItem(String restaurantName, String name, Integer price) {
        if (restaurantMap.get(restaurantName) == null) {
            throw new CustomException("Invalid Resturant");
        }

        Restaurant restaurant = restaurantMap.get(restaurantName);
        restaurant.getMenu().getItems().put(name, price);
        return restaurant;
    }

    public Restaurant deleteItem(String restaurantName, String name) {
        if (restaurantMap.get(restaurantName) == null) {
            throw new CustomException("Invalid Resturant");
        }

        Restaurant restaurant = restaurantMap.get(restaurantName);
        restaurant.getMenu().getItems().remove(name);
        return restaurant;
    }

    public Restaurant addRating(String restaurantName, String review, Integer rating) {
        if (restaurantMap.get(restaurantName) == null) {
            throw new CustomException("Invalid Resturant");
        }

        Restaurant restaurant = restaurantMap.get(restaurantName);
        restaurant.addRatings(new HashMap<>() {{
            put(rating, review);
        }});
        return restaurant;
    }

}
