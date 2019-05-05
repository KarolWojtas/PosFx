package com.karol.posfx.controllers;

import com.karol.posfx.enums.ThemeColors;
import com.karol.posfx.interfaces.Controller;
import com.karol.posfx.enums.SceneCode;
import com.karol.posfx.model.Order;
import com.karol.posfx.model.Report;
import com.karol.posfx.ui.ToggleButtonComponent;
import com.karol.posfx.services.OrderService;
import com.karol.posfx.services.SceneNavigatorService;
import com.karol.posfx.ui.ReportComponent;
import com.karol.posfx.ui.cells.OrderListCell;
import com.karol.posfx.ui.SpinnerComponent;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class OrderListController implements Controller {

    @FXML
    private VBox root;

    @FXML
    private HBox navRoot;

    private ToggleButtonComponent showReportBtn;

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
    private ReportComponent reportComponent = new ReportComponent();
    private double computedReportWidth = 400;
    private int currentOrderListIndex = 0;

    @FXML
    public void initialize() {
        initComponents();
        initList();

        OrderService.getInstance().fetchOrders().subscribe(orders -> {
            this.orders.addAll(orders);
            contentRoot.toFront();
            reportComponent.setReport(new Report(orders));
        });
    }

    private void initList() {
        orderListView.setCellFactory(param -> {
            OrderListCell cell = new OrderListCell(getCurrentColor());
            cell.setAlignment(Pos.CENTER);
            return cell;
        });
        orderListView.setItems(orders);
    }

    private void initComponents() {
        SpinnerComponent spinner = new SpinnerComponent("zapisywanie");
        stackPane.getChildren().add(spinner);
        reportPane.getChildren().add(reportComponent);

        showReportBtn = new ToggleButtonComponent(false);
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
                reportPane.setPrefWidth(computedReportWidth);
                showReportLabel.setText(showReportSelectedText);
            } else {
                reportPane.setPrefWidth(0);
                showReportLabel.setText(showReportDeselectedText);
            }
        });
        spinner.toFront();
    }
    private Color getCurrentColor(){
        if(currentOrderListIndex >= ThemeColors.values().length){
            currentOrderListIndex = 0;
        }
        Color color = ThemeColors.values()[currentOrderListIndex].getColor();
        currentOrderListIndex++;
        return color;
    }
}
