package com.karol.posfx.services;

import com.karol.posfx.enums.ProductId;
import com.karol.posfx.model.Product;
import com.karol.posfx.model.ProductControl;
import com.karol.posfx.ui.MenuCategoryComponent;
import com.karol.posfx.tasks.InitializeMenuComponentsTask;
import com.karol.posfx.tasks.InitializeMenuTask;
import com.karol.posfx.tasks.InitializeProductControlsTask;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Klasa odpowiada za inicjalizacje i przechowywanie menu
 */
public class ProductService {

    private static ProductService ourInstance;
    private static List<Product> productList = new ArrayList<>();
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

    public static ObservableList<ProductControl> getProductControls() {
        return productControls;
    }

    public static void resetProductControls(){
        productControls.forEach(ProductControl::resetQuantity);
    }

    public static List<Product> getProductList() {
        return productList;
    }

    public static Optional<Product> getProduct(ProductId productId){
        return productList.stream().filter(product -> product.getProductId() == productId).findFirst();
    }
}
