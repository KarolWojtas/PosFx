package com.karol.ui;

import com.karol.controllers.ToggleButtonController;
import com.karol.interfaces.Component;
import javafx.beans.property.BooleanProperty;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ToggleButtonComponent extends VBox implements Component {
    private ToggleButtonController controller = new ToggleButtonController();

    public ToggleButtonComponent(){
        HBox view =(HBox) loadViewFromFxml("/views/components/toggleButton.fxml", controller);
        initStandard(this, view);
        view.setAlignment(Pos.CENTER);
        setVgrow(view, Priority.ALWAYS);
    }
    public BooleanProperty selectedProperty(){
        return controller.stateProperty();
    }

}
