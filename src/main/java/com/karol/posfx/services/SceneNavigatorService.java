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
            FXMLLoader loader = new FXMLLoader(getClass().getResource(code.getFxmlResourceUri()));
            Parent root = loader.load();
            Scene scene = new Scene(root, window.getScene().getWidth(), window.getScene().getHeight());
            window.setScene(scene);
        } catch(IOException e){
            System.out.println("scene navigator error: " + e.getMessage());
        }
    }
}
