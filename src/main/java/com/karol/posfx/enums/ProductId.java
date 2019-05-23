package com.karol.posfx.enums;

public enum ProductId {
    BEER("piwo"), TEA("herbata"), ORANGE_JUICE("sok jabłkowy"),
    PASTA_BOLOGNESE("makaron Bolognese"), STEAK("stek wołowy"), EMPTY("empty"),
    WHITE_WINE("wino białe"), WATER("woda"), MUSHROOM_RISOTTO("risotto z grzybami");
    private String name;
    ProductId(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
