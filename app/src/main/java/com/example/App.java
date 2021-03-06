/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.example;

import com.example.crons.MarkOrderFulfilled;
import com.example.service.OrderService;
import com.example.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;

import java.util.Timer;

@Slf4j
public class App {
    public static void main(String[] args) {
        RestaurantService restaurantService = new RestaurantService();
        OrderService orderService = new OrderService(restaurantService);
        Timer t = new Timer();
        MarkOrderFulfilled markOrderFulfilled = new MarkOrderFulfilled(orderService);
        t.scheduleAtFixedRate(markOrderFulfilled, 0, 3000);
    }
}
