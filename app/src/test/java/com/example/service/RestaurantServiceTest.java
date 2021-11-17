package com.example.service;

import com.example.model.Restaurant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class RestaurantServiceTest {
    RestaurantService restaurantService;


    @BeforeEach
    public void init() {
        restaurantService = new RestaurantService();
    }

    @Test
    void testAddRestaurant() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Pav_Bhaji", 100);
        map.put("Chole_Bhutre", 100);

        Restaurant restaurant = restaurantService.addRestaurant("Test1", map, 10, "Delhi");
        assertNotNull(restaurant);
        assertNotNull(restaurant.getMenu());
        assertEquals(restaurant.getName(), "Test1");
        assertEquals(restaurant.getLocation(), "Delhi");
    }

    @Test
    void testGetRestaurantByName() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Pav_Bhaji", 100);
        map.put("Chole_Bhutre", 100);

        restaurantService.addRestaurant("Test1", map, 10, "Delhi");
        Restaurant response = restaurantService.getRestaurantByName("Test1");
        assertNotNull(response);
        assertEquals(response.getName(), "Test1");
    }

    @Test
    void testAddOrUpdateItem() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Pav_Bhaji", 100);
        map.put("Chole_Bhutre", 100);

        Restaurant restaurant = restaurantService.addRestaurant("Test1", map, 10, "Delhi");
        restaurantService.addOrUpdateItem(restaurant.getName(), "Pav_Bhaji", 200);
        Restaurant response = restaurantService.getRestaurantByName("Test1");
        assertNotNull(response);
        assertEquals(response.getMenu().getItems().get("Pav_Bhaji"), 200);
    }

    @Test
    void testDeleteItem() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Pav_Bhaji", 100);
        map.put("Chole_Bhutre", 100);

        Restaurant restaurant = restaurantService.addRestaurant("Test1", map, 10, "Delhi");
        restaurantService.deleteItem(restaurant.getName(), "Pav_Bhaji");
        Restaurant response = restaurantService.getRestaurantByName("Test1");
        assertNotNull(response);
        assertNull(response.getMenu().getItems().get("Pav_Bhaji"));
    }
}
