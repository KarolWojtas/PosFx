package com.karol.services;

import com.karol.model.Product;
import com.karol.model.ProductControl;
import com.karol.tasks.InitializeMenuComponentsTask;
import com.karol.tasks.InitializeMenuTask;
import com.karol.tasks.InitializeProductControlsTask;
import com.karol.ui.MenuCategoryComponent;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.ObservableList;
import javafx.concurrent.Worker;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Klasa odpowiada za inicjalizacje i przechowywanie menu
 */
public class ProductService {

    private static ProductService ourInstance;
    private static List<Product> productList;
    private static ObservableList<ProductControl> productControls;
    private static List<MenuCategoryComponent> menuCategories;
    private static final InitializeProductControlsTask productControlsTask = new InitializeProductControlsTask();
    private static final InitializeMenuComponentsTask menuComponentsTask = new InitializeMenuComponentsTask();
    private static final InitializeMenuTask menuTask = new InitializeMenuTask();
    private static ExecutorService executor = Executors.newSingleThreadExecutor();
    private static Subject<List<MenuCategoryComponent>> menuCategoriesSubject = BehaviorSubject.create();

    public static ProductService getInstance() {
        if(ourInstance == null){
            onCreate();
        } else {
            return ourInstance;
        }
        return ourInstance;
    }

    private ProductService() {
    }

    private static void onCreate(){
        ourInstance = new ProductService();
        initMenuAsync();
    }

    private static void initMenuAsync(){
        menuTask.setOnSucceeded(event -> {
            productList = menuTask.getValue();
            productControlsTask.setProducts(productList);
            executor.execute(productControlsTask);
        });

        productControlsTask.setOnSucceeded(event -> {
            productControls = productControlsTask.getValue();
            menuComponentsTask.setProductControls(productControls);
            executor.execute(menuComponentsTask);
        });

        menuComponentsTask.setOnSucceeded(event -> {
            menuCategories = menuComponentsTask.getValue();
            executor.shutdownNow();
            menuCategoriesSubject.onNext(menuCategories);
        });

        executor.execute(menuTask);
    }

    public static Subject<List<MenuCategoryComponent>> getMenuCategoriesSubject() {
        return menuCategoriesSubject;
    }

    public static List<MenuCategoryComponent> getMenuCategories() {
        return menuCategories;
    }
}
