package com.karol.controllers;

import com.karol.enums.SceneCode;
import com.karol.interfaces.Controller;
import com.karol.services.ProductService;
import com.karol.services.SceneNavigatorService;
import com.karol.ui.MenuCategoryComponent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.List;

public class CreateOrderController implements Controller {
    @FXML
    private VBox root;

    @FXML
    private HBox navRoot;

    @FXML
    private Button goToMainBtn;

    @FXML
    private HBox contentRoot;

    @FXML
    private StackPane stackRoot;

    @FXML
    private ScrollPane scrollRoot;

    @FXML
    private VBox categoryListRoot;

    @FXML
    private VBox orderSummaryRoot;
    @FXML
    private VBox summaryListRoot;
    @FXML
    private Button summaryConfirmBtn;
    @FXML
    private HBox summaryActionRoot;

    @Override
    @FXML
    public void initialize() {
        goToMainBtn.getStyleClass().add("navButton");
        navRoot.getStyleClass().add("navBar");
        summaryActionRoot.getStyleClass().add("navBar");

        orderSummaryRoot.prefWidthProperty().bind(contentRoot.widthProperty().multiply(0.3));
        ProductService.getMenuCategoriesSubject().subscribe(menuCategoryComponents ->
            menuCategoryComponents.forEach(menuCategoryComponent -> {
                menuCategoryComponent.getStyleClass().add("menuCategory");
                categoryListRoot.getChildren().add(menuCategoryComponent);
            })
        );
    }
    @FXML
    public void navigateToMain(){
        SceneNavigatorService.getInstance().go(SceneCode.MAIN_SCENE);
    }
}
