package com.karol.posfx.ui;

import com.karol.posfx.controllers.MenuItemController;
import com.karol.posfx.enums.Category;
import com.karol.posfx.interfaces.Component;
import com.karol.posfx.model.ProductControl;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class MenuItemComponent extends VBox implements Component {
    private VBox view;
    private MenuItemController controller = new MenuItemController();
    private ProductControl productControl;

    public MenuItemComponent(ProductControl productControl){
        this.productControl = productControl;
        controller.setProductControl(productControl);
        view =(VBox) loadViewFromFxml("/views/components/menuItem.fxml", controller);
        initStandard(this, view);
        view.setAlignment(Pos.CENTER);
        setVgrow(view, Priority.ALWAYS);
    }
    public Category getCategory(){
        return productControl.getCategory();
    }
}
