package com.karol.posfx.controllers;

import com.karol.posfx.interfaces.Controller;
import com.karol.posfx.ui.MenuItemComponent;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

import java.util.List;

public class MainCategoryController implements Controller {
    @FXML
    private Label categoryTitleLbl;
    @FXML
    private FlowPane itemFlowRoot;
    @FXML
    private HBox categoryTitleRoot;

    private StringProperty title = new SimpleStringProperty();

    private List<MenuItemComponent> menuItems;
    @Override
    public void initialize() {
        categoryTitleLbl.textProperty().bind(title);
        categoryTitleLbl.getStyleClass().add("categoryTitle");
        categoryTitleRoot.getStyleClass().add("categoryTitleBox");
        itemFlowRoot.getChildren().addAll(menuItems);
    }
    public void setTitleText(String title){
        this.title.setValue(title);
    }

    public void setMenuItems(List<MenuItemComponent> menuItems) {
        this.menuItems = menuItems;
    }
}
