package com.karol.posfx.ui;

import com.karol.posfx.controllers.SpinnerController;
import com.karol.posfx.interfaces.Component;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class SpinnerComponent extends VBox implements Component {

    private VBox view;
    private SpinnerController spinnerController = new SpinnerController();

    public SpinnerComponent(String text){
        spinnerController.setText(text);
        initializeView();
    }

    private void initializeView(){
        view = (VBox) loadViewFromFxml("/views/components/spinner.fxml", spinnerController);

        view.setAlignment(Pos.CENTER);
        view.getStyleClass().add("spinnerRoot");
        setVgrow(view, Priority.ALWAYS);

        initStandard(this, view);

    }

    public void pauseAnimation(){
        spinnerController.pauseAnimation();
    }
    public void playAnimation(){
        spinnerController.playAnimation();
    }
    public void setText(String text){
        spinnerController.setText(text);
    }
}
