package com.karol.posfx.controllers;

import com.karol.posfx.enums.ThemeColors;
import com.karol.posfx.interfaces.Controller;
import com.karol.posfx.model.Report;
import com.karol.posfx.ui.ReportItemComponent;
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

    private ReportItemComponent totalIncomeReportItem;
    private ReportItemComponent totalVolumeOfDrinksReportItem;
    @Override
    public void initialize() {
        totalIncomeReportItem = new ReportItemComponent();
        totalVolumeOfDrinksReportItem = new ReportItemComponent();
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

    public void setReport(Report report) {
        this.totalIncomeReportItem.setupItem("Suma cen zamównień", String.format("%.2f", report.getTotalIncome()), "zł");
        this.totalVolumeOfDrinksReportItem.setupItem("Łączna pojemność napojów", String.format("%.2f", report.getTotalVolumeOfDrinks()), "litr");
    }
}
