package com.karol.model;

import com.karol.enums.Pungency;

public class Dish extends Product{
    private int weightGrams;
    private Pungency pungency;

    @Override
    public String[] getDescription() {
        return new String[0];
    }

    public static class Builder extends Product.Builder<Dish, Builder>{
        @Override
        protected Dish createProduct() {
            return new Dish();
        }

        @Override
        protected Builder createSelf() {
            return this;
        }

        public Builder weightGrams(int weightGrams){
            product.setWeightGrams(weightGrams);
            return this;
        }
        public Builder pungency(Pungency pungency){
            product.setPungency(pungency);
            return this;
        }
    }

    public int getWeightGrams() {
        return weightGrams;
    }

    public void setWeightGrams(int weightGrams) {
        this.weightGrams = weightGrams;
    }

    public Pungency getPungency() {
        return pungency;
    }

    public void setPungency(Pungency pungency) {
        this.pungency = pungency;
    }
}
