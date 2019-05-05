package com.karol.posfx.tasks;

import com.karol.posfx.model.ProductControl;
import com.karol.posfx.ui.MenuCategoryComponent;
import com.karol.posfx.ui.MenuItemComponent;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;


import java.util.*;
import java.util.stream.Collectors;

public class InitializeMenuComponentsTask extends Task<List<MenuCategoryComponent>> {
    private ObservableList<ProductControl> productControls;
    @Override
    protected List<MenuCategoryComponent> call() throws Exception {
        if(productControls == null){
            return Collections.emptyList();
        }
        Map<String, List<MenuItemComponent>> menuCategoriesMap = productControls.stream()
                .filter(productControl -> productControl.getCategory() != null)
                .map(MenuItemComponent::new)
                .collect(Collectors.groupingBy(menuItemComponent -> menuItemComponent.getCategory().getName()));
        return menuCategoriesMap.entrySet().stream().map(entry -> new MenuCategoryComponent(entry.getKey(), entry.getValue())).collect(Collectors.toList());
    }

    public void setProductControls(ObservableList<ProductControl> productControls) {
        this.productControls = productControls;
    }
}