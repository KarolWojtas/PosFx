package com.karol.enums;

import javafx.css.PseudoClass;

public enum Action {
    ORDER_SAVE_SUCCESS("Złożenie zamówenia powiodło się!", PseudoClass.getPseudoClass("success")), ORDER_SAVE_FAIL("Złożenie zamównia nie powiodło się :-(", PseudoClass.getPseudoClass("error"));

    private String message;
    private PseudoClass pseudoClass;

    Action(String message, PseudoClass pseudoClass) {
        this.message = message;
        this.pseudoClass = pseudoClass;
    }

    public String getMessage() {
        return message;
    }

    public PseudoClass getPseudoClass() {
        return pseudoClass;
    }
}
