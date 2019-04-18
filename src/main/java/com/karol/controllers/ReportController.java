package com.karol.controllers;

import com.karol.enums.ThemeColors;
import com.karol.interfaces.Controller;
import com.karol.ui.ReportItemComponent;
import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ReportController implements Controller {
    @FXML
    private VBox reportRoot;
    @FXML
    private ScrollPane reportScrollPane;
    @FXML
    private VBox reportContentRoot;

    private int currentColorIndex = 0;

    @Override
    public void initialize() {
        ReportItemComponent totalIncomeReportItem = new ReportItemComponent();
        ReportItemComponent totalVolumeOfDrinksReportItem = new ReportItemComponent();
        ReportItemComponent[] reportItems = {totalIncomeReportItem, totalVolumeOfDrinksReportItem};
        colorReportItems(reportItems);
        reportContentRoot.getChildren().addAll(reportItems);
    }

    private void colorReportItems(ReportItemComponent[] reportItems){
        for(ReportItemComponent reportItem : reportItems){
            reportItem.setThemeColor(ThemeColors.values()[currentColorIndex]);
            if(currentColorIndex < ThemeColors.values().length - 1){
                ++currentColorIndex;
            } else {
                currentColorIndex = 0;
            }
        }
    }

}
