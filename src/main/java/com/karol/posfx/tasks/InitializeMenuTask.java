package com.karol.posfx.tasks;

import com.karol.posfx.enums.Category;
import com.karol.posfx.enums.Pungency;
import com.karol.posfx.model.Dish;
import com.karol.posfx.model.Drink;
import com.karol.posfx.model.Product;
import com.karol.posfx.enums.ProductId;
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
        Drink tea = new Drink.Builder().productId(ProductId.TEA).category(Category.DRINK)
                .isAlcoholic(false).price(5f).volumeLiters(0.3f).build();
        Drink orangeJuice = new Drink.Builder().productId(ProductId.ORANGE_JUICE).category(Category.DRINK)
                .isAlcoholic(false).price(4.5f).volumeLiters(0.4f).build();
        Drink whiteWine = new Drink.Builder().productId(ProductId.WHITE_WINE).category(Category.DRINK)
                .isAlcoholic(true).price(7f).volumeLiters(0.2f).build();
        Drink water = new Drink.Builder().productId(ProductId.WATER).category(Category.DRINK)
                .isAlcoholic(false).price(3f).volumeLiters(0.33f).build();
        return new Drink[]{beer, tea, orangeJuice, whiteWine, water};
    }
    private static Dish[] initDishes(){
        Dish spaghetti = new Dish.Builder().productId(ProductId.PASTA_BOLOGNESE).pungency(Pungency.MILD)
                .weightGrams(300).category(Category.MAIN_COURSE).price(19)
                .build();
        Dish steak = new Dish.Builder().productId(ProductId.STEAK).pungency(Pungency.MEDIUM)
                .weightGrams(400).category(Category.MAIN_COURSE).price(40)
                .build();
        Dish mushroomRIsotto = new Dish.Builder().productId(ProductId.MUSHROOM_RISOTTO).pungency(Pungency.MILD)
                .weightGrams(300).category(Category.MAIN_COURSE).price(27.5f)
                .build();
        return new Dish[]{spaghetti, steak, mushroomRIsotto};
    }
}
