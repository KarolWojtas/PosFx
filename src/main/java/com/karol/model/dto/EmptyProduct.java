package com.karol.model.dto;

import com.karol.enums.ProductId;
import com.karol.model.Product;

public class EmptyProduct extends Product {
    private static EmptyProduct ourInstance = new EmptyProduct();

    public static EmptyProduct getInstance() {
        return ourInstance;
    }

    private EmptyProduct() {
        this.setProductId(ProductId.EMPTY);
        this.setPrice(0f);
    }

    @Override
    public String[] getDescription() {
        return new String[]{"i'm empty"};
    }
}
