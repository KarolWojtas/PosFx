package com.karol.interfaces;

import com.karol.model.Order;

public interface OrderStoreService {
    void saveOrder(Order order);
    void getAllOrders();
}
