package com.karol.posfx.interfaces;

import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.layout.Pane;

import java.io.IOException;

public interface Component {

    default Pane loadViewFromFxml(String resourceUri, Controller controller){
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(resourceUri));
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        fxmlLoader.setControllerFactory((clazzParam) -> controller); // było new SpinnerController()
        try {
            return fxmlLoader.load();
        } catch (IOException e){
            System.out.println("error" + e.getMessage());
            return null;
        }
    }
    default void  initStandard(Pane self, Pane view){
        self.setMaxHeight(Double.MAX_VALUE);
        self.setMaxWidth(Double.MAX_VALUE);
        view.getStylesheets().add("/styles/application.css");
        self.getChildren().add(view);
    }
}
