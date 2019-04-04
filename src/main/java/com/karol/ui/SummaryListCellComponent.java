package com.karol.ui;

import com.karol.interfaces.Controller;
import com.karol.model.ProductControl;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class SummaryListCellComponent{

    @FXML
    private HBox root;
    @FXML
    private Label productNameLbl;

    @FXML
    private Label productQuantityLbl;

    private ProductControl productControl;

    public SummaryListCellComponent(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/components/summaryListCell.fxml"));
        fxmlLoader.setController(this);
        try
        {
            fxmlLoader.load();
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    public void setProductControl(ProductControl productControl) {
        this.productControl = productControl;
        productNameLbl.setText(productControl.getProduct().getName());
        productQuantityLbl.setText(String.valueOf(productControl.getQuantity()));
    }

    public HBox getRoot() {
        return root;
    }
}
