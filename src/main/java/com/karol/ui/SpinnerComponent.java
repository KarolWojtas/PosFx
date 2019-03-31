package com.karol.ui;

import com.karol.controllers.SpinnerController;
import com.karol.interfaces.Component;
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

        setMaxHeight(Double.MAX_VALUE);
        setMaxWidth(Double.MAX_VALUE);
        view.getStylesheets().add("/styles/application.css");
        view.setAlignment(Pos.CENTER);
        view.getStyleClass().add("spinnerRoot");
        getChildren().add(view);
        setVgrow(view, Priority.ALWAYS);
    }

    public void pauseAnimation(){
        spinnerController.pauseAnimation();
    }
    public void playAnimation(){
        spinnerController.playAnimation();
    }
}
