package com.example.strategy;

import com.example.model.Restaurant;
import com.example.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class LocationStrategy implements SearchStrategy {
    RestaurantService restaurantService;

    public LocationStrategy(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    @Override
    public List<Restaurant> apply(String searchKeyWord) {
        Map<String, Restaurant> restaurantMap = restaurantService.getRestaurantMap();
        List<Restaurant> response = new ArrayList<>();

        for (String id : restaurantMap.keySet()) {
            if (restaurantMap.get(id).getLocation().equalsIgnoreCase(searchKeyWord)) {
                response.add(restaurantMap.get(id));
            }
        }
        return response;
    }
}
