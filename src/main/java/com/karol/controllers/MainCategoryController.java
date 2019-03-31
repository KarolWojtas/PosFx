package com.karol.controllers;

import com.karol.interfaces.Controller;
import com.karol.ui.MenuItemComponent;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.RowConstraints;

public class MainCategoryController implements Controller {
    @FXML
    private Label categoryTitleLbl;
    @FXML
    private GridPane itemGridRoot;
    @FXML
    private HBox categoryTitleRoot;

    private StringProperty title = new SimpleStringProperty();
    @Override
    public void initialize() {
        categoryTitleLbl.textProperty().bind(title);
        categoryTitleLbl.getStyleClass().add("categoryTitle");
        categoryTitleRoot.getStyleClass().add("categoryTitleBox");

        MenuItemComponent item = new MenuItemComponent();
        itemGridRoot.add(item, 0, 0);
    }
    public void setTitleText(String title){
        this.title.setValue(title);
    }
}
