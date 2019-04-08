package com.karol.services;

import com.karol.interfaces.OrderStoreService;
import com.karol.model.Order;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class InMemoryOrderService implements OrderStoreService {

    private List<Order> orders = new ArrayList<>();

    @Override
    public void saveOrder(Order order) {
        Task<Order> saveOrderTask = new Task<Order>() {
            @Override
            protected Order call() throws Exception {
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

    }
}
