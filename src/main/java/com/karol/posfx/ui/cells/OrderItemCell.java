package com.karol.posfx.ui.cells;

import com.karol.posfx.model.OrderItem;
import javafx.scene.control.ListCell;

public class OrderItemCell extends ListCell<OrderItem> {
    private OrderItemCellComponent component = new OrderItemCellComponent();
    @Override
    protected void updateItem(OrderItem item, boolean empty) {
        super.updateItem(item, empty);
        if(empty){
            setGraphic(null);
        } else {
            component.setOrderItem(item);
            setGraphic(component.getRoot());
        }
    }
}
