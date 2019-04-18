package com.karol.services;

import com.karol.interfaces.OrderStoreService;
import com.karol.model.Order;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InMemoryOrderStoreService implements OrderStoreService {

    private List<Order> orders = new ArrayList<>();

    @Override
    public void saveOrder(Order order) {
        Task<Order> saveOrderTask = new Task<Order>() {
            @Override
            protected Order call() throws Exception {
                order.setId(UUID.randomUUID().toString());
                orders.add(order);
                Thread.sleep(1000);
                return order;
            }
        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        saveOrderTask.setOnSucceeded(event -> {
            OrderService.getInstance().getSaveOrderSubject().onNext(saveOrderTask.getValue());
            executorService.shutdown();
        });
        executorService.execute(saveOrderTask);
    }

    @Override
    public void getAllOrders() {
        Task<List<Order>> getOrdersTask = new Task<List<Order>>() {
            @Override
            protected List<Order> call() throws Exception {
                Thread.sleep(1000);
                return orders;
            }
        };
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        getOrdersTask.setOnSucceeded(event -> {
            OrderService.getInstance().getGetAllOrdersSubject().onNext(getOrdersTask.getValue());
            executorService.shutdown();
        });
        executorService.execute(getOrdersTask);
    }
}
