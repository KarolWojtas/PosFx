package com.karol.enums;

public enum Category {
    DRINK("Napoje"), MAIN_COURSE("Dana główne"), DESSERT("Desery");

    String name;

    Category(String name) {
        this.name = name;
    }

    public String getName(){
        return this.name;
    }
}
