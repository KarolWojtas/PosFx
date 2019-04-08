package com.karol.ui;

import com.karol.model.ProductControl;
import javafx.beans.property.DoubleProperty;
import javafx.scene.control.ListCell;

public class ProductControlListCell extends ListCell<ProductControl> {
    private SummaryListCellComponent component = new SummaryListCellComponent();
    private double minWidth = 200;

    public ProductControlListCell(DoubleProperty listWidthProperty) {

        /**
         * adds listener to width property of list parent to resize, but width keeping min width
         */
        listWidthProperty.addListener((observable, oldValue, newValue) -> {
            double newValueCasted = (double) newValue;
            if(newValueCasted >= minWidth){
                component.setRootPrefWidth(newValueCasted);
            } else {
                component.setRootPrefWidth(minWidth);
            }
        });
    }

    @Override
    protected void updateItem(ProductControl item, boolean empty) {
        super.updateItem(item, empty);
        if(empty){
            setGraphic(null);
        } else {
            component.setProductControl(item);
            setGraphic(component.getRoot());
        }
    }
}
