package com.karol.ui.cells;

import com.karol.model.Order;
import javafx.scene.control.ListCell;

public class OrderListCell extends ListCell<Order> {
    private OrderListCellComponent component = new OrderListCellComponent();

    @Override
    protected void updateItem(Order item, boolean empty) {
        super.updateItem(item, empty);
        if(empty){
            setGraphic(null);
        } else {
            component.setOrder(item);
            setGraphic(component.getOrderCellRoot());
        }
    }
}
