package com.karol.posfx.services;

import com.karol.posfx.interfaces.SceneNavigator;
import com.karol.posfx.enums.SceneCode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneNavigatorService implements SceneNavigator {

    private static SceneNavigatorService ourInstance = new SceneNavigatorService();
    private Stage window;
    public static SceneNavigatorService getInstance() {

        return ourInstance;
    }

    private SceneNavigatorService() {
    }

    public void setStage(Stage stage){
        this.window = stage;
    }

    @Override
    public void go(SceneCode code) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource(code.getFxmlResourceUri()));
            Scene scene = new Scene(root, window.getScene().getWidth(), window.getScene().getHeight());
            scene.getStylesheets().add(getClass().getResource("/styles/application.css").toString());
            if(code.getCssResourceUri() != null){
                scene.getStylesheets().add(getClass().getResource(code.getCssResourceUri()).toString());
            }
            window.setScene(scene);
        } catch(IOException e){
            System.out.println("scene navigator error: " + e.getMessage());
        }
    }
}
