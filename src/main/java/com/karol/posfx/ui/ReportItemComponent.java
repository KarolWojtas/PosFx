package com.karol.posfx.ui;

import com.karol.posfx.controllers.ReportItemController;
import com.karol.posfx.enums.ThemeColors;
import com.karol.posfx.interfaces.Component;
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
    public void setupItem(String name, String value, String description){
        controller.setup(name, value, description);
    }
}
