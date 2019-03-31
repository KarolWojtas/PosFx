package com.karol.model;

public class Drink extends Product {

    private float volumeLiters;
    private boolean isAlcoholic;

    private Drink(){
    }

    public static class Builder extends Product.Builder<Drink, Builder>{
        @Override
        protected Drink createProduct() {
            return new Drink();
        }

        @Override
        protected Builder createSelf() {
            return this;
        }
        public Builder volumeLiters(float volumeLiters){
            product.setVolumeLiters(volumeLiters);
            return self;
        }
        public Builder isAlcoholic(boolean isAlcoholic){
            product.setAlcoholic(isAlcoholic);
            return self;
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
    public String[] getDescription() {
        return new String[]{String.format("Pojemność: %fl",volumeLiters)};
    }

    @Override
    public String toString() {
        return "Drink{" +
                "volumeLiters=" + volumeLiters +
                ", isAlcoholic=" + isAlcoholic +
                "} " + super.toString();
    }
}
