package com.karol.services;

import com.karol.model.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class InMemoryOrderStoreService extends AbstractOrderStoreService {

    private List<Order> orders = new ArrayList<>();

    @Override
    Order saveOrderHandler(Order order) {
        order.setId(UUID.randomUUID().toString());
        orders.add(order);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        return order;
    }

    @Override
    List<Order> getOrdersHandler() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        return orders;
    }
}
