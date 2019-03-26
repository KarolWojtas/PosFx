package com.karol.tasks;

import com.karol.model.Drink;
import com.karol.model.Product;
import com.karol.model.ProductId;
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
        System.out.println(products);
        return products;
    }

    /**
     * initcializauje napoje
     * @return tablica napojów
     */
    private static Drink[] initDrinks(){
        Drink beer = new Drink.Builder().name("piwo").productid(ProductId.BEER)
                .isAlcoholic(true).price(8f).volumeLiters(0.5f).build();
        Drink tea = new Drink.Builder().name("herbata").productid(ProductId.TEA)
                .isAlcoholic(false).price(5f).volumeLiters(0.3f).build();
        Drink orangeJuice = new Drink.Builder().name("sok pomarańczowy").productid(ProductId.ORANGE_JUICE)
                .isAlcoholic(false).price(4.5f).volumeLiters(0.4f).build();
        return new Drink[]{beer, tea, orangeJuice};
    }
}
