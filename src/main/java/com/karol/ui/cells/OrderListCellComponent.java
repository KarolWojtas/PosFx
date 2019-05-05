package com.karol.ui.cells;

import com.karol.model.Order;
import com.karol.model.OrderItem;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class OrderListCellComponent {
    @FXML
    private VBox orderCellRoot;

    @FXML
    private VBox orderCellContentRoot;

    @FXML
    private HBox orderCellHeaderRoot;

    @FXML
    private VBox orderCellLeftInfoRoot;

    @FXML
    private Label orderCellCreatedLbl;

    @FXML
    private Label orderCellOrdererLbl;

    @FXML
    private StackPane orderCellPriceRoot;

    @FXML
    private Circle orderCellPriceCircle;

    @FXML
    private Text orderCellPriceLbl;

    @FXML
    private ListView<OrderItem> orderCellProductsListView;

    private Order order;
    private ObservableList<OrderItem> orderItems = FXCollections.observableArrayList();

    public OrderListCellComponent() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/components/orderListCell.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
        initComponents();
    }
    private void initComponents(){
        orderCellProductsListView.setCellFactory(param -> new OrderItemCell());
        orderCellProductsListView.setItems(orderItems);
    }

    public void setOrder(Order order) {
        orderCellCreatedLbl.setText(order.getCreated().format(DateTimeFormatter.ofPattern("dd/MM/yyyy - HH:mm:ss")));
        orderCellOrdererLbl.setText(order.getOrderer());
        orderCellPriceLbl.setText(String.valueOf(order.getTotalPrice()));
        orderItems.addAll(order.getProducts());
        this.order = order;
    }

    public void setColor(Color color){
        orderCellPriceCircle.setFill(color);
        orderCellContentRoot.setStyle(String.format("-fx-border-color: rgb(%d%%, %d%%,%d%%);",
                (int) Math.floor(color.getRed()*100),(int) Math.floor(color.getGreen()*100),(int) Math.floor(color.getBlue()*100)));
    }

    public VBox getOrderCellRoot() {
        return orderCellRoot;
    }
}
