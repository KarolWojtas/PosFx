package com.karol.controllers;

import com.karol.enums.ThemeColors;
import com.karol.interfaces.Controller;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

public class ReportItemController implements Controller {
    @FXML
    private HBox reportItemRoot;

    @FXML
    private Label reportItemNameLbl;

    @FXML
    private Text reportItemValueText;

    @FXML
    private Label reportItemDescriptionLbl;

    @FXML
    private Circle reportItemValueCircle;

    private String name;
    private String value = "brak";
    private String description;

    @Override
    public void initialize() {
        reportItemNameLbl.setText(name);
        reportItemValueText.setText(value);
        reportItemDescriptionLbl.setText(description);
    }

    public void setThemeColor(ThemeColors color){
        reportItemNameLbl.setTextFill(color.getColor());
        reportItemValueCircle.setFill(color.getColor());
        reportItemNameLbl.setStyle(String.format("-fx-border-color: rgb(%d%%, %d%%,%d%%);",
                (int) Math.floor(color.getColor().getRed()*100),(int) Math.floor(color.getColor().getGreen()*100),(int) Math.floor(color.getColor().getBlue()*100)));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
