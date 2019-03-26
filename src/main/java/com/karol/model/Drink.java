package com.karol.model;

public class Drink extends Product {

    private float volumeLiters;
    private boolean isAlcoholic;

    private Drink(){
    }

    public static class Builder {
        private Drink drink;

        public Builder(){
            this.drink = new Drink();
        }

        public Builder productid(ProductId productId){
            drink.setProductId(productId);
            return this;
        }

        public Builder price(float price){
            drink.setPrice(price);
            return this;
        }
        public Builder name(String name){
            drink.setName(name);
            return this;
        }
        public Builder volumeLiters(float volumeLiters){
            drink.setVolumeLiters(volumeLiters);
            return this;
        }
        public Builder isAlcoholic(boolean isAlcoholic){
            drink.setAlcoholic(isAlcoholic);
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
