package com.karol.ui.cells;

import com.karol.model.OrderItem;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class OrderItemCellComponent {
    private OrderItem orderItem;
    @FXML
    private HBox orderItemCellRoot;

    @FXML
    private Label orderItemCellNameLbl;

    @FXML
    private Label orderItemCellQuantityLbl;

    public OrderItemCellComponent(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/components/orderItemCell.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void setOrderItem(OrderItem orderItem) {
        this.orderItem = orderItem;
        orderItemCellNameLbl.setText(orderItem.getProduct().getName());
        orderItemCellQuantityLbl.setText(String.valueOf(orderItem.getQuantity()));
    }
    public HBox getRoot(){
        return orderItemCellRoot;
    }
}
