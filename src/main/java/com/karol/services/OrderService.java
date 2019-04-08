package com.karol.services;

import com.karol.interfaces.OrderStoreService;
import com.karol.model.Order;
import com.karol.model.ProductControl;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.time.ZonedDateTime;

public class OrderService {
    private static OrderService ourInstance = new OrderService();
    private OrderStoreService orderStore = new InMemoryOrderService();
    private Subject<Order> saveOrderSubject = PublishSubject.create();
    private Subject<Order> getAllOrdersSubject = PublishSubject.create();
    private StringProperty ordererName = new SimpleStringProperty("Anonim");
    private Task<Order> saveOrderTask;
    public static OrderService getInstance() {
        return ourInstance;
    }

    private OrderService() {
    }

    public void saveOrder(ObservableList<ProductControl> products){
        Order order = new Order();
        order.setOrderer(ordererName.get());
        order.setProducts(products);
        double totalPrice = products.stream().mapToDouble(productControl -> productControl.getQuantity() * productControl.getProduct().getPrice()).sum();
        order.setTotalPrice(totalPrice);
        order.setCreated(ZonedDateTime.now());
        orderStore.saveOrder(order);

    }

    public Subject<Order> getSaveOrderSubject() {
        return saveOrderSubject;
    }

    public Subject<Order> getGetAllOrdersSubject() {
        return getAllOrdersSubject;
    }
}
