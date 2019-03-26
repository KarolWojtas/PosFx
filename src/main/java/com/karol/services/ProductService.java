package com.karol.services;

import com.karol.model.Product;
import com.karol.tasks.InitializeMenuTask;
import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.BehaviorSubject;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Klasa odpowiada za inicjalizacje i przechowywanie menu
 */
public class ProductService {

    private static ProductService ourInstance;
    private static List<Product> productList;
    private static BehaviorSubject<Boolean> isComputingMenu = BehaviorSubject.createDefault(false);

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
        isComputingMenu.onNext(true);
        InitializeMenuTask menuTask = new InitializeMenuTask();
        menuTask.setOnSucceeded(event -> {
            isComputingMenu.onNext(false);
            productList = menuTask.getValue();
        });
        // TODO zrobić executor singleton do użycia w całej aplikacji
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(menuTask);
        executor.shutdown();

    }

    public BehaviorSubject<Boolean> isComputingMenu() {
        return isComputingMenu;
    }
}
