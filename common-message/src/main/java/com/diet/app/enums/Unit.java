package com.diet.app.enums;

import lombok.Getter;

@Getter
public enum Unit {
    G("gram", 100),
    KG("kilogram", 1),
    ML("mililiters", 100),
    L("liters", 1),
    GAL("galon", 1);

    Unit(String name, double targetSize) {
        this.name = name;
        this.targetSize = targetSize;
    }

    private String name;
    private double targetSize;
}
