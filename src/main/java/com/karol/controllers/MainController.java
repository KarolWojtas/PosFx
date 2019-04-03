package com.karol.controllers;

import com.karol.interfaces.Controller;
import com.karol.enums.SceneCode;
import com.karol.services.ProductService;
import com.karol.services.SceneNavigatorService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController implements Controller {
    @FXML
    Button orderListBtn;
    @FXML
    Button createOrderBtn;

    @FXML
    public void initialize(){
        orderListBtn.setText("Lista zamówień");
        createOrderBtn.setText("Stwórz zamówienie");
        orderListBtn.setOnAction(event -> {
            SceneNavigatorService.getInstance().go(SceneCode.ORDER_LIST_SCENE);
        });
        createOrderBtn.setOnAction(event -> SceneNavigatorService.getInstance().go(SceneCode.CREATE_ORDER_SCENE));
    }

}
