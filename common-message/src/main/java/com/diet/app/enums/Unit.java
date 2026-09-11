package com.diet.app.enums;

import com.diet.app.exceptions.NotFoundException;
import lombok.Getter;

import java.util.Arrays;

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

    private final String name;
    private final double targetSize;

    public static Unit fromValue(String value){
        String cleanedValue = value.toLowerCase().trim();
        return Arrays.stream(Unit.values())
                .filter(unit -> unit.name.equals(cleanedValue))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(
                        "Could not find unit with name " + value
                ));
    }
}
