package com.example.service;

import com.example.enums.SearchStrategyType;
import com.example.model.Order;
import com.example.model.Restaurant;
import com.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CatalogServiceTest {
    CatalogService catalogService;
    OrderService orderService;


    @BeforeEach
    public void init() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Pav_Bhaji", 100);
        map.put("Chole_Bhutre", 100);
        RestaurantService restaurantService = new RestaurantService();
        restaurantService.addRestaurant("Test1", map, 10, "Delhi");
        map.put("Chole_Kulche", 150);
        restaurantService.addRestaurant("Test2", map, 10, "Noida");
        catalogService = new CatalogService(restaurantService);
        orderService = new OrderService(restaurantService);
    }

    @Test
    void searchRestaurantByItem() {
        List<Restaurant> restaurantList = catalogService.searchRestaurant("Pav_Bhaji", SearchStrategyType.ITEM);
        assertNotNull(restaurantList);
        assertEquals(restaurantList.size(), 2);
    }

    @Test
    void searchRestaurantByLocation() {
        List<Restaurant> restaurantList = catalogService.searchRestaurant("Noida", SearchStrategyType.LOCATION);
        assertNotNull(restaurantList);
        assertEquals(restaurantList.size(), 1);
    }

    @Test
    void searchRestaurantAndCreateOrder() {
        List<Restaurant> restaurantList = catalogService.searchRestaurant("Pav_Bhaji", SearchStrategyType.ITEM);
        assertNotNull(restaurantList);
        assertEquals(restaurantList.size(), 2);
        User user = new User("test");
        Order order = orderService.createOrder(Arrays.asList("Pav_Bhaji"), restaurantList.get(0), user);
        assertNotNull(order);
    }
}
