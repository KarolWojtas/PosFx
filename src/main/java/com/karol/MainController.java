package com.karol;

import com.karol.model.SceneCode;
import com.karol.services.ProductService;
import com.karol.services.SceneNavigatorService;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class MainController {
    @FXML
    Button orderListBtn;
    @FXML
    Button createOrderBtn;

    @FXML
    public void initialize(){
        orderListBtn.setText("Lista zamówień");
        createOrderBtn.setText("Stwórz zamówienie");
        orderListBtn.setOnAction(event -> {
            System.out.println(event.toString());
            SceneNavigatorService.getInstance().go(SceneCode.ORDER_LIST_SCENE);
        });
        ProductService.getInstance().isComputingMenu()
            .subscribe(value -> {
                if(value){
                    System.out.println("loading");
                } else {
                    System.out.println("idle");
                }
            });
    }

}
