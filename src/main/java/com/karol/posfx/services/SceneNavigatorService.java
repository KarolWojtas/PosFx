package com.karol.posfx.services;

import com.karol.posfx.interfaces.SceneNavigator;
import com.karol.posfx.enums.SceneCode;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneNavigatorService implements SceneNavigator {

    private static SceneNavigatorService ourInstance = new SceneNavigatorService();
    private static String os = System.getProperty("os.name");
    private static final String WINDOWS = "windows";
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
            double width = window.widthProperty().get();
            double height = window.heightProperty().get();
            Scene scene = new Scene(root, width, height);
            window.setScene(scene);
            if(os.toLowerCase().contains(WINDOWS)) {
                window.hide();
                window.show();
            }
        } catch(IOException e){
            System.out.println("scene navigator error: " + e.getMessage());
        }
    }
}
