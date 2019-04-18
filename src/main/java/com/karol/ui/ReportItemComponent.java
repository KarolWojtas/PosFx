package com.karol.ui;

import com.karol.controllers.ReportItemController;
import com.karol.enums.ThemeColors;
import com.karol.interfaces.Component;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ReportItemComponent extends VBox implements Component {
    private HBox view;
    private ReportItemController controller = new ReportItemController();
    public ReportItemComponent(){
        view =(HBox) loadViewFromFxml("/views/components/reportItem.fxml", controller);
        initStandard(this, view);
        view.setAlignment(Pos.CENTER);
        setVgrow(view, Priority.ALWAYS);
    }
    public void setThemeColor(ThemeColors color){
        controller.setThemeColor(color);
    }
}
