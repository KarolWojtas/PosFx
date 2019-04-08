package com.karol.ui;

import com.karol.controllers.ActionStatusController;
import com.karol.enums.Action;
import com.karol.interfaces.Component;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class ActionStatusComponent extends VBox implements Component {
    private VBox view;
    private ActionStatusController controller = new ActionStatusController();
    public ActionStatusComponent(ScrollPane menuComponent){
        controller.setOnConfirm(event -> {
            menuComponent.toFront();
            this.toBack();
        });
        view  = (VBox) loadViewFromFxml("/views/components/actionStatus.fxml", controller);
        initStandard(this, view);
        view.setAlignment(Pos.CENTER);
        setVgrow(view, Priority.ALWAYS);
    }

    public void setAction(Action action) {
        controller.setAction(action);
    }
    public Button getConfirmButton(){
        return controller.getConfirmBtn();
    }
}
