package com.karol.posfx.tasks;

import com.karol.posfx.model.Product;
import com.karol.posfx.model.ProductControl;
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
            return FXCollections.observableArrayList();
        }
        List<ProductControl> productControls = products.stream().map(ProductControl::new).collect(Collectors.toList());
        ObservableList<ProductControl> observableList = FXCollections.observableArrayList(ProductControl.extractor());
        observableList.addAll(productControls);
        return observableList;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }
}
