package com.karol.posfx.controllers;

import com.karol.posfx.interfaces.Controller;
import com.karol.posfx.enums.SceneCode;
import com.karol.posfx.services.OrderService;
import com.karol.posfx.ui.ToggleButtonComponent;
import com.karol.posfx.services.SceneNavigatorService;
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
    private String usingAwsOrderServiceText = "I/O FILE";
    private String usingInMemoryOrderServiceText = "I/O RAM";
    @FXML
    public void initialize(){
        mainNavBar.getStyleClass().add("navBar");
        mainUsernameInput.textProperty().bindBidirectional(OrderService.getInstance().ordererNameProperty());
        switchOrderServiceBtn = new ToggleButtonComponent(OrderService.getInstance().useFileOrderServiceProperty().getValue());
        switchOrderServiceBtn.selectedProperty().bindBidirectional(OrderService.getInstance().useFileOrderServiceProperty());
        switchOrderServiceLbl.setText(OrderService.getInstance().useFileOrderServiceProperty().get() ? usingAwsOrderServiceText : usingInMemoryOrderServiceText);
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
