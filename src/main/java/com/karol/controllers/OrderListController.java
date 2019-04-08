package com.karol.controllers;

import com.karol.interfaces.Controller;
import com.karol.enums.SceneCode;
import com.karol.services.SceneNavigatorService;
import com.karol.ui.SpinnerComponent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class OrderListController implements Controller {

    @FXML
    private Button goToMainBtn;
    @FXML
    private Button toggleSpinner;
    @FXML
    private VBox root;
    @FXML
    private HBox navRoot;
    @FXML
    private StackPane contentRoot;
    @FXML
    private ScrollPane scrollRoot;
    @FXML
    private HBox orderRoot;
    private boolean isLoading = true;
    @FXML
    public void initialize(){
        SpinnerComponent spinner = new SpinnerComponent("zapisywanie");
        contentRoot.getChildren().add(spinner);

        goToMainBtn.getStyleClass().add("navButton");
        toggleSpinner.getStyleClass().add("navButton");
        navRoot.getStyleClass().add("navBar");

        goToMainBtn.setOnAction(event -> {
            SceneNavigatorService.getInstance().go(SceneCode.MAIN_SCENE);
        });
        toggleSpinner.setOnAction(event -> {
            if(isLoading){
                spinner.toBack();
                spinner.pauseAnimation();
                isLoading = false;
            } else {
                spinner.toFront();
                spinner.playAnimation();
                isLoading = true;
            }
        });

    }
}
