package com.karol.tasks;

import com.karol.enums.Category;
import com.karol.enums.Pungency;
import com.karol.model.Dish;
import com.karol.model.Drink;
import com.karol.model.Product;
import com.karol.enums.ProductId;
import javafx.concurrent.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class InitializeMenuTask extends Task<List<Product>> {

    public InitializeMenuTask(){

    }
    @Override
    protected List<Product> call() throws Exception {
        List<Product> products = new ArrayList<>();
        Collections.addAll(products, initDrinks());
        Collections.addAll(products, initDishes());
        return products;
    }

    /**
     * initcializauje napoje
     * @return tablica napojów
     */
    private static Drink[] initDrinks(){
        Drink beer = new Drink.Builder().productId(ProductId.BEER).price(8f)
                .volumeLiters(0.5f).category(Category.DRINK).build();
        Drink tea = new Drink.Builder().productId(ProductId.TEA)
                .isAlcoholic(false).price(5f).volumeLiters(0.3f).build();
        Drink orangeJuice = new Drink.Builder().productId(ProductId.ORANGE_JUICE)
                .isAlcoholic(false).price(4.5f).volumeLiters(0.4f).build();
        return new Drink[]{beer, tea, orangeJuice};
    }
    private static Dish[] initDishes(){
        Dish spaghetti = new Dish.Builder().productId(ProductId.PASTA_BOLOGNESE).pungency(Pungency.MILD)
                .weightGrams(300).category(Category.MAIN_COURSE).price(19)
                .build();
        Dish steak = new Dish.Builder().productId(ProductId.STEAK).pungency(Pungency.MEDIUM)
                .weightGrams(400).category(Category.MAIN_COURSE).price(40)
                .build();
        return new Dish[]{spaghetti, steak};
    }
}
