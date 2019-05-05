package com.karol.posfx.interfaces;

import com.karol.posfx.model.Order;

public interface OrderStoreService {
    void saveOrder(Order order);
    void getAllOrders();
}
