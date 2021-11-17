package com.example.strategy;

import com.example.model.Menu;
import com.example.model.Restaurant;
import com.example.service.RestaurantService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ItemSearchStrategy implements SearchStrategy {
    RestaurantService restaurantService;

    public ItemSearchStrategy(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;

    }

    @Override
    public List<Restaurant> apply(String searchKeyWord) {
        Map<String, Restaurant> restaurantMap = restaurantService.getRestaurantMap();
        TreeMap<Integer, List<Restaurant>> response = new TreeMap<>();
        for (String id : restaurantMap.keySet()) {
            Menu menu = restaurantMap.get(id).getMenu();
            if (menu.getItems().get(searchKeyWord) != null) {
                response.putIfAbsent(menu.getItems().get(searchKeyWord), new ArrayList<>());
                response.get(menu.getItems().get(searchKeyWord)).add(restaurantMap.get(id));
            }
        }

        List<Restaurant> restaurantList = new ArrayList<>();
        for (Integer key : response.keySet()) {
            for (Restaurant restaurant : response.get(key)) {
                restaurantList.add(restaurant);
            }
        }

        return restaurantList;
    }
}
