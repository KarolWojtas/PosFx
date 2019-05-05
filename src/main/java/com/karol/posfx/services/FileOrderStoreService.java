package com.karol.posfx.services;

import com.karol.posfx.model.Order;

import java.util.List;
import java.util.UUID;

public class FileOrderStoreService extends AbstractOrderStoreService{
    private FileService fileService = FileService.getInstance();

    @Override
    Order saveOrderHandler(Order order) {
        order.setId(UUID.randomUUID().toString());
        return fileService.saveOrderToFile(order, "orders.json");
    }

    @Override
    List<Order> getOrdersHandler() {
        return fileService.readOrdersFromFile("orders.json");
    }
}
