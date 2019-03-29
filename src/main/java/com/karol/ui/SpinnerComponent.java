package com.karol.ui;

import com.karol.controllers.SpinnerController;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Pos;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class SpinnerComponent extends VBox {

    private VBox view;
    private SpinnerController spinnerController = new SpinnerController();

    public SpinnerComponent(){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/components/spinner.fxml"));
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        fxmlLoader.setControllerFactory((clazzParam) -> spinnerController); // było new SpinnerController()
        setMaxHeight(Double.MAX_VALUE);
        setMaxWidth(Double.MAX_VALUE);
        try {
            view = fxmlLoader.load();
            view.getStylesheets().add("/styles/application.css");
            view.setAlignment(Pos.CENTER);
            view.getStyleClass().add("spinnerRoot");
        } catch (IOException e){
            System.out.println("error" + e.getMessage());
        }
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
