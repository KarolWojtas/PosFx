package com.karol.controllers;

import com.karol.enums.SceneCode;
import com.karol.interfaces.Controller;
import com.karol.model.ProductControl;
import com.karol.model.ProductControlListCell;
import com.karol.services.ProductService;
import com.karol.services.SceneNavigatorService;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

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
    private ListView<ProductControl> summaryListView;
    @FXML
    private Button summaryConfirmBtn;
    @FXML
    private HBox summaryActionRoot;

    private ObservableList<ProductControl> summaryProducts = FXCollections.observableArrayList(ProductControl.extractor());

    @Override
    @FXML
    public void initialize() {
        goToMainBtn.getStyleClass().add("navButton");
        navRoot.getStyleClass().add("navBar");
        summaryActionRoot.getStyleClass().add("navBar");

        setupMenuCategories();
        setupSummaryListView();

        orderSummaryRoot.prefWidthProperty().bind(contentRoot.widthProperty().multiply(0.3));
        summaryListView.setCellFactory(param -> new ProductControlListCell());

    }
    @FXML
    public void navigateToMain(){
        SceneNavigatorService.getInstance().go(SceneCode.MAIN_SCENE);
    }

    /**
     * Adds listener to ProductService.menuCategories
     */
    private void setupMenuCategories(){
        ProductService.getMenuCategoriesSubject().subscribe(menuCategoryComponents ->
                menuCategoryComponents.forEach(menuCategoryComponent -> {
                    menuCategoryComponent.getStyleClass().add("menuCategory");
                    categoryListRoot.getChildren().add(menuCategoryComponent);
                })
        );
    }

    /**
     * Adds listener to ProductService.productControls - if any product controls quantity changes from 0 and to 0, it gets added/removed
     */
    private void setupSummaryListView(){
        summaryListView.setItems(summaryProducts);
        ProductService.getProductControls().addListener((ListChangeListener.Change<? extends ProductControl> change) -> {
            while(change.next()){
                if(change.wasUpdated()){
                    for(int i = change.getFrom(); i < change.getTo(); i++){
                        ProductControl updatedProduct = ProductService.getProductControls().get(i);
                        boolean isUpdatedProductInSummary = summaryProducts.contains(updatedProduct);
                        if(updatedProduct.getQuantity() == 0 && isUpdatedProductInSummary){
                            summaryProducts.remove(updatedProduct);
                        }
                        if(updatedProduct.getQuantity() > 0 && !isUpdatedProductInSummary){
                            summaryProducts.add(updatedProduct);
                        }
                    }
                }
            }
        });
    }

}
