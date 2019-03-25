package com.karol;

import com.karol.services.SceneNavigatorService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main extends Application {
    Stage window;
    @Override
    public void start(Stage stage) throws Exception {
        window = stage;
        Parent root = FXMLLoader.load(getClass().getResource("/views/main.fxml"));
        Scene scene = new Scene(root);
        scene.getStylesheets().add(getClass().getResource("/styles/application.css").toString());
        scene.getStylesheets().add(getClass().getResource("/styles/main_scene.css").toString());
        window.setTitle("PosFx");
        window.setScene(scene);
        window.show();
        initServices();
    }

    private void initServices(){
        SceneNavigatorService.getInstance().setStage(window);
    }

    public static void main(String[] args) {
        launch(args);
    }
}