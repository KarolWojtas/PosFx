package com.karol.model;

public class Drink extends Product {

    private float volumeLiters;
    private boolean isAlcoholic;

    private Drink(){
    }

    public static class DrinkBuilder {
        private Drink drink;

        public DrinkBuilder(){
            this.drink = new Drink();
        }

        public DrinkBuilder price(float price){
            drink.setPrice(price);
            return this;
        }
        public DrinkBuilder name(String name){
            drink.setName(name);
            return this;
        }
        public DrinkBuilder volumeLiters(float volumeLiters){
            drink.setVolumeLiters(volumeLiters);
            return this;
        }
        public DrinkBuilder isAlcoholic(boolean isAlcoholic){
            drink.setAlcoholic(isAlcoholic);
            return this;
        }
        public DrinkBuilder category(ProductCategory category){
            drink.setCategory(category);
            return this;
        }

        public Drink build(){
            return drink;
        }
    }

    public Float getVolumeLiters() {
        return volumeLiters;
    }

    public void setVolumeLiters(Float volumeLiters) {
        this.volumeLiters = volumeLiters;
    }

    public boolean isAlcoholic() {
        return isAlcoholic;
    }

    public void setAlcoholic(boolean alcoholic) {
        isAlcoholic = alcoholic;
    }

    @Override
    public String toString() {
        return "Drink{" +
                "volumeLiters=" + volumeLiters +
                ", isAlcoholic=" + isAlcoholic +
                "} " + super.toString();
    }
}
