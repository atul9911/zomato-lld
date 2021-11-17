package com.example.crons;

import com.example.enums.OrderStatus;
import com.example.model.Order;
import com.example.service.OrderService;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

@Slf4j
public class MarkOrderFulfilled extends TimerTask {
    OrderService orderService;

    public MarkOrderFulfilled(OrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public void run() {
        List<Order> orderList = new ArrayList<>(orderService.getOrderMap().values());
        for (Order order : orderList) {
            log.info("Updating Order with Order Id :{}", order.getId());
            orderService.updateOrderStatus(order, OrderStatus.FULFILLED);
        }
    }
}
