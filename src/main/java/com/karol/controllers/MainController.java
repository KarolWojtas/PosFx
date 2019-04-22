package com.karol.controllers;

import com.karol.interfaces.Controller;
import com.karol.enums.SceneCode;
import com.karol.services.OrderService;
import com.karol.services.ProductService;
import com.karol.services.SceneNavigatorService;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class MainController implements Controller {
    @FXML
    private Button orderListBtn;
    @FXML
    private Button createOrderBtn;
    @FXML
    private HBox mainNavBar;
    @FXML
    private TextField mainUsernameInput;

    @FXML
    public void initialize(){
        mainNavBar.getStyleClass().add("navBar");
        mainUsernameInput.textProperty().bindBidirectional(OrderService.getInstance().ordererNameProperty());
        orderListBtn.setText("Lista zamówień");
        createOrderBtn.setText("Stwórz zamówienie");
        orderListBtn.setOnAction(event -> {
            SceneNavigatorService.getInstance().go(SceneCode.ORDER_LIST_SCENE);
        });
        createOrderBtn.setOnAction(event -> SceneNavigatorService.getInstance().go(SceneCode.CREATE_ORDER_SCENE));
    }

}
