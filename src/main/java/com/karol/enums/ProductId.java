package com.karol.enums;

public enum ProductId {
    BEER("piwo"), TEA("herbata"), ORANGE_JUICE("sok jabłkowy"),
    PASTA_BOLOGNESE("makaron Bolognese"), STEAK("stek wołowy");
    private String name;
    ProductId(String name){
        this.name = name;
    }
}
