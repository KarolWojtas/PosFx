package com.karol.controllers;

import com.karol.interfaces.Controller;
import com.karol.enums.SceneCode;
import com.karol.services.OrderService;
import com.karol.services.SceneNavigatorService;
import com.karol.ui.ToggleButtonComponent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    private Label switchOrderServiceLbl;

    private ToggleButtonComponent switchOrderServiceBtn;
    private String usingAwsOrderServiceText = "I/O AWS";
    private String usingInMemoryOrderServiceText = "I/O RAM";
    @FXML
    public void initialize(){
        mainNavBar.getStyleClass().add("navBar");
        mainUsernameInput.textProperty().bindBidirectional(OrderService.getInstance().ordererNameProperty());
        switchOrderServiceBtn = new ToggleButtonComponent();
        switchOrderServiceBtn.selectedProperty().bindBidirectional(OrderService.getInstance().useAwsOrderServiceProperty());
        switchOrderServiceLbl.setText(OrderService.getInstance().useAwsOrderServiceProperty().get() ? usingAwsOrderServiceText : usingInMemoryOrderServiceText);
        mainNavBar.getChildren().add(3, switchOrderServiceBtn);

        orderListBtn.setText("Lista zamówień");
        createOrderBtn.setText("Stwórz zamówienie");
        orderListBtn.setOnAction(event -> {
            SceneNavigatorService.getInstance().go(SceneCode.ORDER_LIST_SCENE);
        });
        createOrderBtn.setOnAction(event -> SceneNavigatorService.getInstance().go(SceneCode.CREATE_ORDER_SCENE));

        switchOrderServiceBtn.selectedProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue){
                switchOrderServiceLbl.setText(usingAwsOrderServiceText);
            } else {
                switchOrderServiceLbl.setText(usingInMemoryOrderServiceText);
            }
        });
    }

}
