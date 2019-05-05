package com.karol.posfx.services;

import com.karol.posfx.interfaces.OrderStoreService;
import com.karol.posfx.model.Order;
import javafx.concurrent.Task;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

abstract public class AbstractOrderStoreService implements OrderStoreService {
    abstract Order saveOrderHandler(Order order);
    abstract List<Order> getOrdersHandler();

    @Override
    public void saveOrder(Order order) {
        Task<Order> saveOrderTask = new Task<Order>() {
            @Override
            protected Order call() throws Exception {
                return saveOrderHandler(order);
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
                List<Order> orders = getOrdersHandler();
                return orders.stream().sorted((o1, o2) -> o1.getCreated().isBefore(o2.getCreated()) ? 1 : -1).collect(Collectors.toList());
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
