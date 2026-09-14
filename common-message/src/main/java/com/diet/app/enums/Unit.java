package com.diet.app.enums;

import com.diet.app.exceptions.NotFoundException;
import lombok.Getter;

import java.util.Arrays;

import static java.util.Objects.isNull;

@Getter
public enum Unit {
    G("gram", "g", 1, null, 100),
    KG("kilogram", "kg", 1000, Unit.G, 1),
    ML("milliliters", "ml", 1, null, 100),
    L("liters", "l",1000, Unit.ML, 1);

    Unit(String name, String symbol, double toBaseMultiplayer, Unit unit,
         double baseRepresentationAmount) {
        this.name = name;
        this.symbol = symbol;
        this.toBaseMultiplayer = toBaseMultiplayer;
        this.baseUnit = unit;
        this.baseRepresentationAmount = baseRepresentationAmount;
    }

    private final String name;
    private final String symbol;
    private final double toBaseMultiplayer;
    private final Unit baseUnit;
    private final double baseRepresentationAmount;

    public Unit getBaseUnit(){
        return isNull(this.baseUnit) ? this : this.baseUnit;
    }

    public static Unit fromValue(String value){
        String cleanedValue = value.toLowerCase().trim();
        try{
            return fromName(cleanedValue);
        } catch (NotFoundException e) {
            return fromSymbol(cleanedValue);
        }
    }

    private static Unit fromName(String value){
        return Arrays.stream(Unit.values())
                .filter(unit -> unit.name.equals(value))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(
                        "Could not find unit with name " + value
                ));
    }

    private static Unit fromSymbol(String value){
        return Arrays.stream(Unit.values())
                .filter(unit -> unit.symbol.equals(value))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(
                        "Could not find unit with name " + value
                ));
    }
}
