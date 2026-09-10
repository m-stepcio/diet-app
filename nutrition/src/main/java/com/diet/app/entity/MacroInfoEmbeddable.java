package com.diet.app.entity;

import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class MacroInfoEmbeddable {

    private double kcal;
    private double protein;
    private double fat;
    private double carbohydrates;
}