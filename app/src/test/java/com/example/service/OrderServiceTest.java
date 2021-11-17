package com.example.service;

import com.example.enums.OrderStatus;
import com.example.exception.CustomException;
import com.example.model.Order;
import com.example.model.Restaurant;
import com.example.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    Restaurant restaurant;
    OrderService orderService;

    @BeforeEach
    public void init() {
        RestaurantService restaurantService = new RestaurantService();
        Map<String, Integer> map = new HashMap<>();
        map.put("Pav_Bhaji", 100);
        map.put("Chole_Bhutre", 100);
        restaurant = restaurantService.addRestaurant("Test1", map, 10, "Delhi");
        orderService = new OrderService(restaurantService);
    }

    @Test
    void createOrder() {
        User user = new User("Test");
        Order order = orderService.createOrder(Arrays.asList("Pav_Bhaji"), restaurant, user);
        assertNotNull(order);
        assertNotNull(order.getRestaurant());
        assertNotNull(order.getItemsList());
        assertEquals(order.getStatus(), OrderStatus.PROCESSING);
        assertEquals(order.getRestaurant().getName(), restaurant.getName());
        assertTrue(order.getItemsList().contains("Pav_Bhaji"));
    }

    @Test
    void createOrderWithHighCapacity() {
        User user = new User("Test");
        restaurant.setCurrentCapacity(10);
        CustomException thrown = assertThrows(
            CustomException.class,
            () -> orderService.createOrder(Arrays.asList("Pav_Bhaji"), restaurant, user),
            "Expected doThing() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Resturant Can not fulfill the order"));
    }

    @Test
    void testCapacityAfterOrderFulfillment(){
        User user = new User("Test");
        Order order = orderService.createOrder(Arrays.asList("Pav_Bhaji"), restaurant, user);
        assertNotNull(order);
        assertNotNull(order.getRestaurant());
        assertNotNull(order.getItemsList());
        assertEquals(order.getStatus(), OrderStatus.PROCESSING);
        assertEquals(order.getRestaurant().getName(), restaurant.getName());
        assertTrue(order.getItemsList().contains("Pav_Bhaji"));
        assertEquals(restaurant.getCurrentCapacity(),1);
        orderService.updateOrderStatus(order,OrderStatus.FULFILLED);
        assertEquals(restaurant.getCurrentCapacity(),0);

    }
}
