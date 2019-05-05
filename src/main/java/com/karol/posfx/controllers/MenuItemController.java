package com.karol.posfx.controllers;

import com.karol.posfx.interfaces.Controller;
import com.karol.posfx.model.ProductControl;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class MenuItemController implements Controller {
    @FXML
    private VBox root;

    @FXML
    private HBox titleRoot;

    @FXML
    private Label titleLbl;

    @FXML
    private VBox descriptionRoot;

    @FXML
    private HBox actionRoot;

    @FXML
    private Button decreaseBtn;

    @FXML
    private Label quantityLbl;

    @FXML
    private Button increaseBtn;

    private ProductControl productControl;

    public MenuItemController(ProductControl productControl) {
        this.productControl = productControl;
    }
    public MenuItemController(){}

    @Override
    public void initialize() {
        quantityLbl.textProperty().bind(productControl.quantityProperty().asString());
        titleLbl.setText(productControl.getProduct().getName());

        root.getStyleClass().add("menuItemRoot");
        increaseBtn.getStyleClass().addAll("menuItemAction", "menuItemIncreaseBtn");
        decreaseBtn.getStyleClass().addAll("menuItemAction", "menuItemDecreaseBtn");
        titleLbl.getStyleClass().add("categoryTitle");
    }
    @FXML
    public void onIncrease(){
        productControl.increase();
    }
    @FXML
    public void onDecrease(){
        productControl.decrease();
    }

    public void setProductControl(ProductControl productControl) {
        this.productControl = productControl;
    }
}
