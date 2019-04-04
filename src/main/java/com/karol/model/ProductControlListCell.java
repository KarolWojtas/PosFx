package com.karol.model;

import com.karol.ui.SummaryListCellComponent;
import javafx.scene.control.ListCell;

public class ProductControlListCell extends ListCell<ProductControl> {

    @Override
    protected void updateItem(ProductControl item, boolean empty) {
        super.updateItem(item, empty);
        if(empty){
            setGraphic(null);
        } else {
            SummaryListCellComponent component = new SummaryListCellComponent();
            component.setProductControl(item);
            setGraphic(component.getRoot());
        }
    }
}
