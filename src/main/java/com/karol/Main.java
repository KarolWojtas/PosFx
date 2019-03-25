package com.karol;

import com.karol.model.Drink;
import com.karol.model.Product;
import com.karol.services.SceneNavigatorService;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


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
        Drink herbata = new Drink.DrinkBuilder()
                .name("herbata").price(1.23f).volumeLiters(0.2f).build();
        Drink piwo = new Drink.DrinkBuilder()
                .name("piwo").price(5.5f).volumeLiters(0.5f).build();
        List<Product> products = new ArrayList<>();
        Collections.addAll(products, herbata, piwo);
        System.out.println(products);
    }

    public static void main(String[] args) {
        launch(args);
    }
}