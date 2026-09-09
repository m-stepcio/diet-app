package com.diet.app.enums;

public enum Unit {
    G("gram"),
    KG("kilogram"),
    ML("mililiters"),
    L("liters"),
    GAL("galon");

    Unit(String name) {
        this.name = name;
    }

    private String name;
}
