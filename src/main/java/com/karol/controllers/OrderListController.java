package com.karol.controllers;

import com.karol.interfaces.Controller;
import com.karol.enums.SceneCode;
import com.karol.model.Order;
import com.karol.services.OrderService;
import com.karol.services.SceneNavigatorService;
import com.karol.ui.ReportComponent;
import com.karol.ui.ToggleButtonComponent;
import com.karol.ui.cells.OrderListCell;
import com.karol.ui.SpinnerComponent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class OrderListController implements Controller {

    @FXML
    private VBox root;

    @FXML
    private HBox navRoot;

    private ToggleButtonComponent showReportBtn;

    @FXML
    private Region spreadRegion;

    @FXML
    private Button goToMainBtn;

    @FXML
    private StackPane stackPane;

    @FXML
    private HBox contentRoot;

    @FXML
    private VBox reportPane;

    @FXML
    private Label showReportLabel;

    @FXML
    private ListView<Order> orderListView;

    private ObservableList<Order> orders = FXCollections.observableArrayList();
    private String showReportSelectedText = "Ukryj raport";
    private String showReportDeselectedText = "Pokaż raport";

    @FXML
    public void initialize() {
        initComponents();
        initList();

        OrderService.getInstance().fetchOrders().subscribe(orders -> {
            this.orders.addAll(orders);
            contentRoot.toFront();
        });
    }

    private void initList() {
        orderListView.setCellFactory(param -> {
            OrderListCell cell = new OrderListCell();
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
        orderListView.setItems(orders);
    }

    private void initComponents() {
        SpinnerComponent spinner = new SpinnerComponent("zapisywanie");
        stackPane.getChildren().add(spinner);
        ReportComponent reportComponent = new ReportComponent();
        reportPane.getChildren().add(reportComponent);

        showReportBtn = new ToggleButtonComponent();
        navRoot.getChildren().add(0, showReportBtn);
        goToMainBtn.getStyleClass().add("navButton");
        navRoot.getStyleClass().add("navBar");
        reportPane.setPrefWidth(0);

        goToMainBtn.setOnAction(event -> {
            SceneNavigatorService.getInstance().go(SceneCode.MAIN_SCENE);
        });

        if(showReportBtn.selectedProperty().getValue()){
            showReportLabel.setText(showReportSelectedText);
        } else {
            showReportLabel.setText(showReportDeselectedText);
        }
        showReportBtn.selectedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                reportPane.prefWidthProperty().bind(stackPane.widthProperty().multiply(0.25));
                showReportLabel.setText(showReportSelectedText);
            } else {
                reportPane.prefWidthProperty().unbind();
                reportPane.setPrefWidth(0);
                showReportLabel.setText(showReportDeselectedText);
            }
        });
        spinner.toFront();
    }
}
