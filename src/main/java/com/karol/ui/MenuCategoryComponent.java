package com.karol.ui;

import com.karol.controllers.MainCategoryController;
import com.karol.interfaces.Component;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.List;

public class MenuCategoryComponent extends VBox implements Component{
    private VBox view;
    private MainCategoryController controller = new MainCategoryController();

    public MenuCategoryComponent(String title, List<MenuItemComponent> menuItems) {
        controller.setTitleText(title);
        controller.setMenuItems(menuItems);
        view  = (VBox) loadViewFromFxml("/views/components/mainCategory.fxml", controller);
        initStandard(this, view);
        view.setAlignment(Pos.CENTER);
        setVgrow(view, Priority.ALWAYS);
    }
}
