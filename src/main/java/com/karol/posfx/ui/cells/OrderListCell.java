package com.karol.posfx.ui.cells;

import com.karol.posfx.model.Order;
import javafx.scene.control.ListCell;
import javafx.scene.paint.Color;

public class OrderListCell extends ListCell<Order> {
    private OrderListCellComponent component = new OrderListCellComponent();
    private Color color;

    public OrderListCell(Color color) {
        this.color = color;
        setMouseTransparent(true);
    }

    @Override
    protected void updateItem(Order item, boolean empty) {
        super.updateItem(item, empty);
        if(empty){
            setGraphic(null);
        } else {
            component.setOrder(item);
            component.setColor(color);
            setGraphic(component.getOrderCellRoot());
        }
    }
}
