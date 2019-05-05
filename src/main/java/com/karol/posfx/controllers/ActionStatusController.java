package com.karol.posfx.controllers;

import com.karol.posfx.enums.Action;
import com.karol.posfx.interfaces.Controller;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class ActionStatusController implements Controller {
    @FXML
    private VBox root;

    @FXML
    private Label messageLbl;

    @FXML
    private Button confirmBtn;

    private Action action;
    private StringProperty messageProperty = new SimpleStringProperty("");

    private EventHandler<ActionEvent> onConfirm;

    @Override
    @FXML
    public void initialize() {
        root.getStyleClass().add("actionStatusRoot");
        confirmBtn.getStyleClass().add("actionStatusConfirmBtn");
        messageLbl.textProperty().bind(messageProperty);
        confirmBtn.setOnAction(onConfirm);
    }

    public void setAction(Action action) {
        this.action = action;
        messageProperty.setValue(action.getMessage());
    }

    public void setOnConfirm(EventHandler<ActionEvent> onConfirm) {
        this.onConfirm = onConfirm;
    }

    public Button getConfirmBtn() {
        return confirmBtn;
    }
}
