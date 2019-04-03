package com.karol.tasks;

import com.karol.model.Product;
import com.karol.model.ProductControl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;

import java.util.List;
import java.util.stream.Collectors;

public class InitializeProductControlsTask extends Task<ObservableList<ProductControl>> {
    private List<Product> products;

    @Override
    protected ObservableList<ProductControl> call() {
        if(products == null){
            return FXCollections.emptyObservableList();
        }
        List<ProductControl> productControls = products.stream().map(ProductControl::new).collect(Collectors.toList());
        return FXCollections.observableArrayList(productControls);
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }
}