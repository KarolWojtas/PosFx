package com.karol.ui;

import com.karol.controllers.ReportController;
import com.karol.interfaces.Component;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ReportComponent extends VBox implements Component {
    private VBox view;
    private ReportController controller = new ReportController();
    public ReportComponent(){
        view =(VBox) loadViewFromFxml("/views/components/report.fxml", controller);
        initStandard(this, view);
        view.setAlignment(Pos.CENTER);
        setVgrow(view, Priority.ALWAYS);
    }
}
