package com.karol.services;

import com.karol.interfaces.OrderStoreService;
import com.karol.model.Order;
import com.karol.model.ProductControl;
import io.reactivex.subjects.PublishSubject;
import io.reactivex.subjects.Subject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.time.ZonedDateTime;
import java.util.List;

public class OrderService {
    private static InMemoryOrderStoreService inMemoryOrderStoreService = new InMemoryOrderStoreService();
    private static FileOrderStoreService fileOrderStoreService = new FileOrderStoreService();
    private static OrderService ourInstance = new OrderService();
    private OrderStoreService orderStore = inMemoryOrderStoreService;
    private Subject<Order> saveOrderSubject = PublishSubject.create();
    private Subject<List<Order>> getAllOrdersSubject = PublishSubject.create();
    private StringProperty ordererName = new SimpleStringProperty("Anonim");
    private BooleanProperty useFileOrderService = new SimpleBooleanProperty(false);
    public static OrderService getInstance() {
        return ourInstance;
    }

    //TODO dodać przełączanie między usługami AWS / RAM

    private OrderService() {
        useFileOrderServiceProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                orderStore = fileOrderStoreService;
            } else {
                orderStore = inMemoryOrderStoreService;
            }
        });
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
    public Subject<List<Order>> fetchOrders(){
        orderStore.getAllOrders();
        return getAllOrdersSubject;
    }

    public Subject<Order> getSaveOrderSubject() {
        return saveOrderSubject;
    }

    public Subject<List<Order>> getGetAllOrdersSubject() {
        return getAllOrdersSubject;
    }

    public StringProperty ordererNameProperty() {
        return ordererName;
    }

    public BooleanProperty useFileOrderServiceProperty() {
        return useFileOrderService;
    }
}
