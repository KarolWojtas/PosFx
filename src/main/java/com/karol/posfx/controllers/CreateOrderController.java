package com.karol.posfx.controllers;

import com.karol.posfx.enums.Action;
import com.karol.posfx.enums.SceneCode;
import com.karol.posfx.interfaces.Controller;
import com.karol.posfx.model.Order;
import com.karol.posfx.model.ProductControl;
import com.karol.posfx.services.OrderService;
import com.karol.posfx.ui.ActionStatusComponent;
import com.karol.posfx.ui.cells.ProductControlListCell;
import com.karol.posfx.services.ProductService;
import com.karol.posfx.services.SceneNavigatorService;
import com.karol.posfx.ui.SpinnerComponent;
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
    private SpinnerComponent spinner;
    private ActionStatusComponent actionStatusComponent;

    @Override
    @FXML
    public void initialize() {
        goToMainBtn.getStyleClass().add("navButton");
        navRoot.getStyleClass().add("navBar");
        summaryActionRoot.getStyleClass().add("navBar");
        spinner = new SpinnerComponent("ładowanie menu");
        actionStatusComponent = new ActionStatusComponent(scrollRoot);
        // actionStatusComponent.getStyleClass().add("actionStatusRoot");
        stackRoot.getChildren().addAll(actionStatusComponent, spinner);
        spinner.toFront();
        if(summaryProducts.isEmpty()){
            summaryConfirmBtn.setDisable(true);
        }

        setupMenuCategories();
        setupSummaryListView();

        orderSummaryRoot.prefWidthProperty().bind(contentRoot.widthProperty().multiply(0.3));
        summaryListView.setCellFactory(param -> new ProductControlListCell(summaryListView.prefWidthProperty()));

        summaryConfirmBtn.setOnAction(event -> {
            summaryConfirmBtn.setDisable(true);
            spinner.setText("Zapisywanie zamoówienia");
            spinner.toFront();
            OrderService.getInstance().saveOrder(summaryProducts);
        });

        OrderService.getInstance().getSaveOrderSubject().subscribe(this::handleSaveOrderSuccess, this::handleSaveOrderError);
    }

    @FXML
    public void navigateToMain() {
        ProductService.resetProductControls();
        SceneNavigatorService.getInstance().go(SceneCode.MAIN_SCENE);
    }

    /**
     * Adds listener to ProductService.menuCategories
     */
    private void setupMenuCategories() {
        ProductService.getMenuCategoriesSubject().subscribe(menuCategoryComponents -> {
                    menuCategoryComponents.forEach(menuCategoryComponent -> {
                        menuCategoryComponent.getStyleClass().add("menuCategory");
                        categoryListRoot.getChildren().add(menuCategoryComponent);
                    });
                    scrollRoot.toFront();
                }
        );
    }

    /**
     * Adds listener to ProductService.productControls - if any product controls quantity changes from 0 and to 0, it gets added/removed
     */
    private void setupSummaryListView() {
        summaryListView.setItems(summaryProducts);
        ProductService.getProductControls().addListener((ListChangeListener.Change<? extends ProductControl> change) -> {
            while (change.next()) {
                if (change.wasUpdated()) {
                    for (int i = change.getFrom(); i < change.getTo(); i++) {
                        ProductControl updatedProduct = ProductService.getProductControls().get(i);
                        boolean isUpdatedProductInSummary = summaryProducts.contains(updatedProduct);
                        if (updatedProduct.getQuantity() == 0 && isUpdatedProductInSummary) {
                            summaryProducts.remove(updatedProduct);
                        }
                        if (updatedProduct.getQuantity() > 0 && !isUpdatedProductInSummary) {
                            summaryProducts.add(updatedProduct);
                        }
                    }
                    if(summaryProducts.isEmpty()){
                        summaryConfirmBtn.setDisable(true);
                    } else {
                        summaryConfirmBtn.setDisable(false);
                    }
                }
            }
        });
    }

    private void resetSummaryList(){
        summaryProducts.clear();
    }

    private void handleSaveOrderSuccess(Order order){
        summaryConfirmBtn.setDisable(false);
        this.resetSummaryList();
        ProductService.resetProductControls();
        spinner.toBack();
        actionStatusComponent.getConfirmButton().pseudoClassStateChanged(Action.ORDER_SAVE_SUCCESS.getPseudoClass(), true);
        actionStatusComponent.setAction(Action.ORDER_SAVE_SUCCESS);
        actionStatusComponent.toFront();
    }

    private void handleSaveOrderError(Throwable error){
        summaryConfirmBtn.setDisable(false);
        actionStatusComponent.getConfirmButton().pseudoClassStateChanged(Action.ORDER_SAVE_SUCCESS.getPseudoClass(), false);
        error.printStackTrace();
    }
}
